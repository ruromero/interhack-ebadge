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
import eu.europa.ec.interhack.ebadge.qr.encode.Encoder;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingException;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingOptions;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingOptions.ImageType;
import eu.europa.ec.interhack.ebadge.repository.VisitorRepository;

/**
 * Created by rromero on 10/10/16.
 */
@RestController
public class VisitorResource {

	private static final String QRCODE_FOLDER = System.getProperty("user.dir");

	@Autowired
	private VisitorRepository repo;

	@CrossOrigin(value = "*")
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse register(@RequestBody Visitor visitor) {

		// TODO placeholder for validation

		// blindly save it to the db for the moment
		repo.insert(visitor);

		return new VisitorResponse("OK");
	}

	@CrossOrigin(value = "*")
	@RequestMapping(value = "/check", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse checkVisitor(@RequestParam(value = "visitor") Visitor visitor) {

		// TODO here check an already registered visitor

		// TODO query the db to check whether the user exists or not
		// further checks

		boolean validationOk = true;

		if (validationOk) {

			// crate the qr-code UPON VALIDATION
			EncodingOptions options = new EncodingOptions();
			options.setSize(340);
			options.setImageType(ImageType.PNG);
			options.setMargin(1);
			options.setForegroundColor(Color.BLACK);
			String name = "" + new Random().nextInt(99);

			try {
				File out = Encoder.encode(QRCODE_FOLDER, name, visitor.getId(), options);
			} catch (EncodingException e) {
				e.printStackTrace();
			}
			
			// TODO send email shipping the QR code

			return new VisitorResponse("OK");
		}
		
		return new VisitorResponse("NOK");
	}
}
