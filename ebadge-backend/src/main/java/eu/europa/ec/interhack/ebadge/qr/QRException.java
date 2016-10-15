package eu.europa.ec.interhack.ebadge.qr;

public class QRException extends Exception {

	private static final long serialVersionUID = -8377023312475734443L;

	public QRException() {
	}

	public QRException(String message) {
		super(message);
	}

	public QRException(String message, Exception cause) {
		super(message, cause);
	}

	public QRException(Throwable cause) {
		super(cause);
	}

}
