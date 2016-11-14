package eu.europa.ec.interhack.ebadge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class MailService {

	private static final String SUBJECT_PREFIX = "[eBadge - InterHack]";

	@Value("${email.username}")
	private String username;

	@Value("${email.password}")
	private String password;

	@Value("${email.host}")
	private String host;

	@Value("${email.port}")
	private String port;

	private Properties props;

	@PostConstruct
	private void configure() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
	}

	public void sendEmail(String toEmail, String subject, String content, String qrCodeFile, String pdfFile) {
		System.out.println("preparing email...");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(SUBJECT_PREFIX + subject);
			message.setText(content);

			System.out.println("Attaching files");
			System.out.println("Working on file: "+qrCodeFile);
			// QR code attachment
			MimeBodyPart qrMessageBodyPart = new MimeBodyPart();
			DataSource qrSource = new FileDataSource(qrCodeFile);
			qrMessageBodyPart.setDataHandler(new DataHandler(qrSource));
			qrMessageBodyPart.setFileName(qrCodeFile);
			
			// PDF file attachment
			System.out.println("working on file: "+pdfFile);
			MimeBodyPart pdfMessageBodyPart = new MimeBodyPart();
			DataSource pdfSource = new FileDataSource(pdfFile);
			pdfMessageBodyPart.setDataHandler(new DataHandler(pdfSource));
			pdfMessageBodyPart.setFileName(pdfFile);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(qrMessageBodyPart);
			multipart.addBodyPart(pdfMessageBodyPart);
			message.setContent(multipart);

			System.out.println("sending email...");
			Transport.send(message);

			System.out.println("Email sent successfully");

		} catch (MessagingException e) {
			System.out.println("Something went wrong while trying to send the email");
			throw new RuntimeException(e);
		}
	}
	
	public void sendEmail(String toEmail, String subject, String body) {
		System.out.println("preparing email...");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(SUBJECT_PREFIX + subject);
			message.setText(body);

			System.out.println("sending email...");
			Transport.send(message);

			System.out.println("Email sent successfully");

		} catch (MessagingException e) {
			System.out.println("Something went wrong while trying to send the email");
			throw new RuntimeException(e);
		}
	}
	
}
