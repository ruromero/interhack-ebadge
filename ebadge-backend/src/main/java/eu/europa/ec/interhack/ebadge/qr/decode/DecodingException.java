package eu.europa.ec.interhack.ebadge.qr.decode;

public class DecodingException extends Exception {

	private static final long serialVersionUID = -8377023312475734443L;

	public DecodingException() {
	}

	public DecodingException(String message) {
		super(message);
	}

	public DecodingException(String message, Exception cause) {
		super(message, cause);
	}

	public DecodingException(Throwable cause) {
		super(cause);
	}

}
