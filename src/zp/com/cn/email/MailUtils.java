package zp.com.cn.email;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;

public class MailUtils extends Object {
	final static Properties props = new Properties();

	private static Session getSession() throws NamingException {
		// props.put("mail.smtp.auth" ,"true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "10.1.1.170");
		// props.put("mail.smtp.user" ,"780210");
		// props.put("mail.smtp.password" ,"654123");
		// props.put("mail.from", "SYS_TEM");
		return Session.getDefaultInstance(props);

		// return (Session) new InitialContext().lookup(mailName);
	}

	private static void sendMessageWithAttachment(String from, String[] to,
			String[] cc, String subject, String content, String mimeType,
			File attachment) throws Exception {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (from != null && !"".equals(from.trim())) {
			InternetAddress sentFrom = new InternetAddress(from);
			message.setFrom(sentFrom);
		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i]);
		}

		if ((cc.length > 0) && (cc[0] != null)) {
			InternetAddress[] copyTo = new InternetAddress[cc.length];

			for (int i = 0; i < cc.length; i++) {
				copyTo[i] = new InternetAddress(cc[i]);
			}

			message.setRecipients(Message.RecipientType.CC, copyTo);
		}

		message.setRecipients(Message.RecipientType.TO, sendTo);

		message.setSubject(subject);

		// create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setContent(content, mimeType);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();

		DataSource source = new FileDataSource(attachment);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(attachment.getName());

		multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		message.setContent(multipart);

		Transport.send(message);
	}

	private static void sendMessage(String from, String[] to, String[] cc,
			String subject, String content, String mimeType) throws Exception {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (from != null && !"".equals(from.trim())) {
			InternetAddress sentFrom = new InternetAddress(from);
			message.setFrom(sentFrom);
		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i] + "@163.com");
		}

		if ((cc.length > 0) && (cc[0] != null)) {
			InternetAddress[] copyTo = new InternetAddress[cc.length];

			for (int i = 0; i < cc.length; i++) {
				copyTo[i] = new InternetAddress(cc[i] + "@163.com");
			}

			message.setRecipients(Message.RecipientType.CC, copyTo);
		}

		message.setRecipients(Message.RecipientType.TO, sendTo);

		message.setSubject(subject);
		message.setContent(content, mimeType);

		Transport.send(message);
	}

	private static void sendMessage1(String from, String[] to, String[] cc,
			String subject, String content, String mimeType) throws Exception {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (from != null && !"".equals(from.trim())) {
			InternetAddress sentFrom = new InternetAddress(from);
			message.setFrom(sentFrom);
		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i]);
		}

		if ((cc.length > 0) && (cc[0] != null)) {
			InternetAddress[] copyTo = new InternetAddress[cc.length];

			for (int i = 0; i < cc.length; i++) {
				copyTo[i] = new InternetAddress(cc[i]);
			}

			message.setRecipients(Message.RecipientType.CC, copyTo);
		}

		message.setRecipients(Message.RecipientType.TO, sendTo);

		message.setSubject(subject);
		message.setContent(content, mimeType);

		Transport.send(message);
	}

	final public static void sendMessage(String from, String to, String cc,
			String subject, String content, String mimeType) throws Exception {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessage(from, recipient, copy, subject, content, mimeType);
	}

	final public static void sendMessage(String from, String to, String cc,
			String subject, String content, String mimeType, File attachment)
			throws Exception {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessageWithAttachment(from, recipient, copy, subject, content,
				mimeType, attachment);
	}

	public static void sendTextMessage(String from, String[] to, String[] cc,
			String subject, String content) throws Exception {
		sendMessage(from, to, cc, subject, content, "text/plain;charset=gb2312");
	}

	public static void sendTextMessage(String from, String to, String cc,
			String subject, String content) throws Exception {
		String[] recipient = { to };
		String[] copy = { cc };
		sendTextMessage(from, recipient, copy, subject, content);
	}

	public static void sendHTMLMessage(String from, String[] to, String[] cc,
			String subject, String content) throws Exception {
		sendMessage(from, to, cc, subject, content, "text/html;charset=gb2312");
	}
	
	public static void sendHTMLMessage1(String from, String[] to, String[] cc,
			String subject, String content) throws Exception {
		sendMessage1(from, to, cc, subject, content, "text/html;charset=gb2312");
	}

	public static void sendHTMLMessage(String from, String to, String cc,
			String subject, String content) throws Exception {
		String[] recipient = { to };
		String[] copy = { cc };
		sendHTMLMessage(from, recipient, copy, subject, content);
	}
}
