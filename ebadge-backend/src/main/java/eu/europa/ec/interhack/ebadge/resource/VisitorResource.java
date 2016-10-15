package eu.europa.ec.interhack.ebadge.resource;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.europa.ec.interhack.ebadge.dto.VisitorResponse;
import eu.europa.ec.interhack.ebadge.model.Visitor;
import eu.europa.ec.interhack.ebadge.model.CommonData.Institution;
import eu.europa.ec.interhack.ebadge.qr.encode.Encoder;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingException;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingOptions;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingOptions.ImageType;
import eu.europa.ec.interhack.ebadge.qr.pdf.PDFGeneratingException;
import eu.europa.ec.interhack.ebadge.qr.pdf.PDFGenerator;
import eu.europa.ec.interhack.ebadge.repository.VisitorRepository;

/**
 * Created by rromero on 10/10/16.
 */
@RestController
public class VisitorResource {

	private static final String QRCODE_FOLDER = System.getProperty("user.dir");

	@Autowired
	private VisitorRepository repo;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse register(@RequestBody Visitor visitor) {

		// TODO placeholder for validation

		// blindly save it to the db for the moment
		visitor.setStatus("PENDING");
		repo.insert(visitor);

		return new VisitorResponse("OK");
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse accept(@RequestParam(value = "visitor") Visitor visitor) {

		visitor.setStatus("ACCEPTED");
		repo.save(visitor);
		
		// crate the qr-code UPON VALIDATION
		EncodingOptions options = new EncodingOptions();
		options.setSize(340);
		options.setImageType(ImageType.PNG);
		options.setMargin(1);
		options.setForegroundColor(Color.BLACK);
		String name = "" + new Random().nextInt(99);

		File out;
		try {
			out = Encoder.encode(QRCODE_FOLDER, name, visitor.getId(), options);
		} catch (EncodingException e) {
			e.printStackTrace();
			return new VisitorResponse("NOK").setComment("QR code generation failed");
		}
		
		// generate the pdf file
		File pdfFile;
		try {
			pdfFile = PDFGenerator.generate(QRCODE_FOLDER, name, visitor, out.getAbsolutePath(), Institution.COUNCIL);
		} catch (PDFGeneratingException e) {
			e.printStackTrace();
			return new VisitorResponse("NOK").setComment("PDF generation failed");
		}

		// send email shipping the QR code and PDF
		new MailSender().sendEmail(visitor.getEmail(), "[edBage - InterHack] Your eBadge is ready", out.getAbsolutePath(), pdfFile.getAbsolutePath());

		return new VisitorResponse("OK");
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse reject(@RequestParam(value = "visitor") Visitor visitor) {

		// TODO handle rejection
		visitor.setStatus("REJECTED");
		repo.save(visitor);
		
		new MailSender().sendEmail(visitor.getEmail(), "[edBage - InterHack] Your request has been rejected");
		
		return new VisitorResponse("OK");
	}
} 
