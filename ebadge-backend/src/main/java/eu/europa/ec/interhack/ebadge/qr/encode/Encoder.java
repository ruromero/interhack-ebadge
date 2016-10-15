package eu.europa.ec.interhack.ebadge.qr.encode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Encoder {

	public static File encode(String content, String fileName, String outputDirectory, EncodingOptions options) throws EncodingException {
		if (fileName == null || "".equals(fileName) || outputDirectory == null || "".equals(outputDirectory)) {
			throw new IllegalArgumentException("File name and output directory need to be specified!");
		}

		int size = options.getSize();
		String fileType = options.getImageType().getType();
		try {
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, options.getCharset());
			hintMap.put(EncodeHintType.MARGIN, options.getMargin());
			hintMap.put(EncodeHintType.ERROR_CORRECTION, options.getErrorCorrectionLevel().getLevel());

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hintMap);
			int width = bitMatrix.getWidth();
			BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);

			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(options.getBackgroundColor());
			graphics.fillRect(0, 0, width, width);
			graphics.setColor(options.getForegroundColor());

			for (int idx = 0; idx < width; idx++) {
				for (int jdx = 0; jdx < width; jdx++) {
					if (bitMatrix.get(idx, jdx)) {
						graphics.fillRect(idx, jdx, 1, 1);
					}
				}
			}

			File outputFile = new File(new StringBuilder(outputDirectory).append(fileName).append(".").append(options.getImageType().getType()).toString());
			ImageIO.write(image, fileType, outputFile);

			return outputFile;
		} catch (WriterException | IOException e) {
			throw new EncodingException("An exception has occured while trying to generate QR: " + e.getMessage(), e);
		}
	}

}
