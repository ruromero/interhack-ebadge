package eu.europa.ec.interhack.ebadge.qr.decode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

public class Decoder {

	public static String decode(String fileName) throws DecodingException {
		try {
			File imageFile = new File(fileName);
			BufferedImage image = ImageIO.read(imageFile);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Reader reader = new QRCodeReader();

			Result result = reader.decode(bitmap);
			return result.getText();
		} catch (IOException | NotFoundException | ChecksumException | FormatException e) {
			throw new DecodingException("Error while decoding QR: " + e.getMessage(), e);
		}

	}
}
