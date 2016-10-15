package eu.europa.ec.interhack.ebadge.qr.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import eu.europa.ec.interhack.ebadge.model.Visitor;
import eu.europa.ec.interhack.ebadge.model.CommonData.Institution;

public class PDFGenerator {

	public static void generate(Visitor userData, String qrImagePath, Institution institution) {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("e:/tools/ws/out/01.pdf"));
			document.open();
			document.add(new Paragraph(institution.getName()));

			Image image1 = Image.getInstance("src/main/resources/logos/council.png");
			image1.scaleAbsolute(100, 100);
			document.add(image1);

			image1 = Image.getInstance(qrImagePath);
			image1.scaleAbsolute(360, 360);
			document.add(image1);

			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
