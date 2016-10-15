package eu.europa.ec.interhack.ebadge.qr.encode;

public class EncodingException extends Exception {

	private static final long serialVersionUID = -8377023312475734443L;

	public EncodingException() {
	}

	public EncodingException(String message) {
		super(message);
	}

	public EncodingException(String message, Exception cause) {
		super(message, cause);
	}

	public EncodingException(Throwable cause) {
		super(cause);
	}

}
