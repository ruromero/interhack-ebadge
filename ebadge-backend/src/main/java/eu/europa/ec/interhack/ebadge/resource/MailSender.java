package eu.europa.ec.interhack.ebadge.resource;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	private Properties props;
	
	public MailSender() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "ssl0.ovh.net");
		props.put("mail.smtp.port", "465");
	}
	
	public void sendEmail(String toEmail) {
		System.out.println("preparing email...");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ebadge@damas.be", "ebadge!01");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ebadge-interhack@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject("Ebadge - InterHack: Your eBadge is ready");
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
