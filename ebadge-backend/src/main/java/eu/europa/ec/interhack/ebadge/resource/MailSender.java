package eu.europa.ec.interhack.ebadge.resource;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

	private Properties props;

	public MailSender() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.sendgrid.net");
		props.put("mail.smtp.port", "25");
	}

	public void sendEmail(String toEmail, String subject, String qrCodeFile, String pdfFile) {
		System.out.println("preparing email...");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("azure_29d137e2b8785ad1119f54150755dbb5@azure.com", "InterHack2016_smtp");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ebadge-interhack@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			System.out.println("Attaching files");
			// QR code attachment
			MimeBodyPart qrMessageBodyPart = new MimeBodyPart();
			DataSource qrSource = new FileDataSource(qrCodeFile);
			qrMessageBodyPart.setDataHandler(new DataHandler(qrSource));
			qrMessageBodyPart.setFileName(qrCodeFile);
			
			// PDF file attachment
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
	
	public void sendEmail(String toEmail, String subject) {
		System.out.println("preparing email...");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("azure_29d137e2b8785ad1119f54150755dbb5@azure.com", "InterHack2016_smtp");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ebadge-interhack@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			System.out.println("sending email...");
			Transport.send(message);

			System.out.println("Email sent successfully");

		} catch (MessagingException e) {
			System.out.println("Something went wrong while trying to send the email");
			throw new RuntimeException(e);
		}
	}
	
}
