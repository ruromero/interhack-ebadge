package eu.europa.ec.interhack.ebadge.qr.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import eu.europa.ec.interhack.ebadge.model.CommonData.Institution;
import eu.europa.ec.interhack.ebadge.model.Visitor;

public class PDFGenerator {

	public static File generate(String outputDirectory, String fileName, Visitor userData, String qrImagePath, Institution institution) throws PDFGeneratingException {
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputDirectory + fileName + ".pdf"));
			document.open();

			BaseFont bf = BaseFont.createFont();

			PdfContentByte cb = writer.getDirectContent();
			cb.setFontAndSize(bf, 18);
			cb.beginText();
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, institution.getName(), 100, 796, 0);
			cb.endText();
			cb.setLineWidth(1f);
			cb.moveTo(0, 765);
			cb.lineTo(595, 765);
			cb.stroke();

			Image logo = Image.getInstance("src/main/resources/logos/" + institution.getLogo() + ".png");
			logo.setAbsolutePosition(10, 770);
			logo.scaleToFit(70, 70);
			document.add(logo);

			Font font = FontFactory.getFont("Arial", 14);
			document.add(new Paragraph(100, "Temporary Badge", font));
			document.add(new Paragraph("Expires: " + userData.getExpirationDate(), font));

			font.setSize(22);
			document.add(new Paragraph(userData.getFirstName() + " " + userData.getLastName().toUpperCase(), font));
			document.add(new Paragraph(userData.getIdDocNumber(), font));

			Image qr = Image.getInstance(qrImagePath);
			qr.setAlignment(PdfContentByte.ALIGN_CENTER);
			document.add(qr);

			document.close();
			writer.close();
			
			return new File(outputDirectory + fileName + ".pdf");
		} catch (DocumentException | IOException e) {
			throw new PDFGeneratingException("Error generating PDF: " + e.getMessage(), e);
		}
	}

}
