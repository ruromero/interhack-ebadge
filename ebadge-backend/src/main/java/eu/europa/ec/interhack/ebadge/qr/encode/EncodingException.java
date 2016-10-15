package eu.europa.ec.interhack.ebadge.qr.encode;

import eu.europa.ec.interhack.ebadge.qr.QRException;

public class EncodingException extends QRException {

	public EncodingException(String message, Exception e) {
		super(message, e);
	}

	private static final long serialVersionUID = -8377023312475734443L;
}
