package eu.europa.ec.interhack.ebadge.resource;

import java.awt.Color;
import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.europa.ec.interhack.ebadge.model.CommonData.Institution;
import eu.europa.ec.interhack.ebadge.model.Visitor;
import eu.europa.ec.interhack.ebadge.qr.encode.Encoder;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingException;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingOptions;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingOptions.ImageType;
import eu.europa.ec.interhack.ebadge.qr.pdf.PDFGeneratingException;
import eu.europa.ec.interhack.ebadge.qr.pdf.PDFGenerator;

/**
 * Created by rromero on 10/10/16.
 */
@RestController
public class HelloResource {
	
	private static final String QRCODE_FOLDER = System.getProperty("user.dir");

    @RequestMapping("/hello")
    public String greetings(@RequestParam(value="name", defaultValue = "world!") String name) {
		// crate the qr-code UPON VALIDATION
		EncodingOptions options = new EncodingOptions();
		options.setSize(340);
		options.setImageType(ImageType.PNG);
		options.setMargin(1);
		options.setForegroundColor(Color.BLACK);

		File out;
		try {
			out = Encoder.encode(QRCODE_FOLDER, name, name, options);
		} catch (EncodingException e) {
			e.printStackTrace();
			return "ops";
		}
		
		// generate the pdf file
		File pdfFile;
		try {
			Visitor userData = new Visitor();
			userData.setFirstName("Daniel");
			userData.setLastName("Chirita");
			userData.setVisitDate("2016-10-15");
			userData.setExpirationDate("2016-10-25");
			userData.setIdDocNumber("RO 123765");
			pdfFile = PDFGenerator.generate(QRCODE_FOLDER, name, userData, out.getAbsolutePath(), Institution.COUNCIL);
		} catch (PDFGeneratingException e) {
			e.printStackTrace();
			return "ops";
		}

		// send email shipping the QR code and PDF
		new MailSender().sendEmail("antdim@gmail.com", "Your eBadge is ready", out.getAbsolutePath(), pdfFile.getAbsolutePath());
    	
        return "Hello " + name;
    }

}
