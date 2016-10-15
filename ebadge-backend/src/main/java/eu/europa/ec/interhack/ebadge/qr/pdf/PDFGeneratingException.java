package eu.europa.ec.interhack.ebadge.qr.pdf;

import eu.europa.ec.interhack.ebadge.qr.QRException;

public class PDFGeneratingException extends QRException {
	public PDFGeneratingException(String message, Exception e) {
		super(message, e);
	}

	private static final long serialVersionUID = 3998450248048138796L;
}
