package eu.europa.ec.interhack.ebadge.qr.decode;

import eu.europa.ec.interhack.ebadge.qr.QRException;

public class DecodingException extends QRException {

	public DecodingException(String message, Exception e) {
		super(message, e);
	}

	private static final long serialVersionUID = -8377023312475734443L;
}
