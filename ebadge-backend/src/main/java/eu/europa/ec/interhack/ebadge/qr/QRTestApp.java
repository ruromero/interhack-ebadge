package eu.europa.ec.interhack.ebadge.qr;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import eu.europa.ec.interhack.ebadge.model.CommonData.Institution;
import eu.europa.ec.interhack.ebadge.model.Visitor;
import eu.europa.ec.interhack.ebadge.qr.decode.Decoder;
import eu.europa.ec.interhack.ebadge.qr.decode.DecodingException;
import eu.europa.ec.interhack.ebadge.qr.encode.Encoder;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingException;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingOptions;
import eu.europa.ec.interhack.ebadge.qr.encode.EncodingOptions.ImageType;
import eu.europa.ec.interhack.ebadge.qr.pdf.PDFGenerator;

public class QRTestApp {

	public static void test(String[] args) throws EncodingException, DecodingException {
		String name = "" + new Random().nextInt(99);

		EncodingOptions options = new EncodingOptions();
		options.setSize(340);
		options.setImageType(ImageType.PNG);
		options.setMargin(1);
		options.setForegroundColor(Color.BLACK);

		String content = "1234-abcdef-001234-ab77ff5";
		File out = Encoder.encode(content, name, "e:/tools/ws/out/", options);

		String fileName = out.getAbsolutePath();
		System.out.println(fileName);
		String result = Decoder.decode(fileName);
		System.out.println(result);

		assert (result.compareTo(content) == 0);

		Visitor userData = new Visitor();
		userData.setFirstName("Daniel");
		userData.setLastName("Chirita");
		userData.setVisitDate("2016-10-15");
		userData.setExpirationDate("2016-10-25");
		userData.setIdDocNumber("RO 123765");
		PDFGenerator.generate(userData, fileName, Institution.EC);
	}
}
