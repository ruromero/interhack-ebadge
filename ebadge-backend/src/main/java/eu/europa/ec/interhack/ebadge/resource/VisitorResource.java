package eu.europa.ec.interhack.ebadge.resource;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.europa.ec.interhack.ebadge.dto.VisitorResponse;
import eu.europa.ec.interhack.ebadge.model.CommonData.Institution;
import eu.europa.ec.interhack.ebadge.model.Visitor;
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
@RequestMapping("/rest/visitor")
public class VisitorResource {

	private static final String QRCODE_FOLDER = System.getProperty("user.dir");

	@Autowired
	private VisitorRepository repo;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse register(@RequestBody Visitor visitor) {

		// Checking that we have the required params coming in
		ArrayList<String> errors = new ArrayList<String>();
		if (StringUtils.isBlank(visitor.getFirstName()))
			errors.add("firstName is a required field");
		if (StringUtils.isBlank(visitor.getLastName()))
			errors.add("lastName is a required field");
		if (StringUtils.isBlank(visitor.getEmail()))
			errors.add("email is a required field");
		if (StringUtils.isBlank(visitor.getIdDocNumber()))
			errors.add("idDocNumber is a required field");
		if (StringUtils.isBlank(visitor.getValidityIdDate()))
			errors.add("validityIdDate is a required field");
		if (StringUtils.isBlank(visitor.getHost()))
			errors.add("host is a required field");

		// If we have missing fields send an error, otherwise push into DB and
		// return a success
		if (errors.size() > 0) {
			return new VisitorResponse("NOK").setComment(StringUtils.join(errors.toArray(new String[] {}), ", "));
		} else {
			// Save it to the DB
			visitor.setStatus("PENDING");
			visitor.setVisitorId(UUID.randomUUID().toString());
			repo.insert(visitor);

			return new VisitorResponse("OK");
		}
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse accept(@RequestParam(value = "visitorId") String visitorId) {

		List<Visitor> visitors = repo.findByVisitorId(visitorId);

		if (visitors == null || visitors.isEmpty()) {
			return new VisitorResponse("NOK").setComment("Visitor not found");
		}

		Visitor visitor = visitors.get(0);
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
		new MailSender().sendEmail(visitor.getEmail(), "Your eBadge is ready", out.getAbsolutePath(),
				pdfFile.getAbsolutePath());

		return new VisitorResponse("OK");
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse reject(@RequestParam(value = "visitorId") String visitorId) {
		List<Visitor> visitors = repo.findByVisitorId(visitorId);

		if (visitors == null || visitors.isEmpty()) {
			return new VisitorResponse("NOK").setComment("Visitor not found");
		}

		Visitor visitor = visitors.get(0);
		visitor.setStatus("REJECTED");
		repo.save(visitor);

		// Send mail and notify the visitor of rejection
		String mailSubject = "Your request to visit the European insititutions has been rejected";
		String mailBody = String.format(
				"Dear %s,\\n\\nYour request to visit %s has been rejected.\\n\\nKind regards,\\nThe eBadge wizards",
				String.format("%s %s", visitor.getFirstName(), visitor.getLastName()), visitor.getHost());
		new MailSender().sendEmail(visitor.getEmail(), mailSubject, mailBody);

		return new VisitorResponse("OK");
	}
}
