package eu.europa.ec.interhack.ebadge.qr.encode;

import java.awt.Color;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class EncodingOptions {

	public enum ImageType {
		PNG("png"), JPEG("jpg");

		private String type;

		private ImageType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	public enum CorrectionLevel {
		LOW(ErrorCorrectionLevel.L), MEDIUM(ErrorCorrectionLevel.M), HIGH(ErrorCorrectionLevel.Q), ULTRA(ErrorCorrectionLevel.H);

		private ErrorCorrectionLevel level;

		private CorrectionLevel(ErrorCorrectionLevel level) {
			this.level = level;
		}

		public ErrorCorrectionLevel getLevel() {
			return level;
		}
	}

	private static final int MAX_SIZE = 340;

	private int size = EncodingOptions.MAX_SIZE;
	private ImageType imageType = ImageType.PNG;
	private String charset = "UTF-8";
	private int margin = 1;
	private CorrectionLevel errorCorrectionLevel = CorrectionLevel.HIGH;
	private Color backgroundColor = Color.WHITE;
	private Color foregroundColor = Color.BLACK;

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public CorrectionLevel getErrorCorrectionLevel() {
		return errorCorrectionLevel;
	}

	public void setErrorCorrectionLevel(CorrectionLevel errorCorrectionLevel) {
		this.errorCorrectionLevel = errorCorrectionLevel;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if (size > EncodingOptions.MAX_SIZE) {
			throw new IllegalArgumentException("Image width cannot exceed " + EncodingOptions.MAX_SIZE + " pixels!");
		}
		this.size = size;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

}
