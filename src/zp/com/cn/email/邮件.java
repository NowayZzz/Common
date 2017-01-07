package zp.com.cn.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class 邮件 {
	static String host="10.1.1.170";
	String username="741059";
	String mailHead="邮件头";
	String mailTo="741059";
	String mailFrom="741059";
	static String mailSubject="邮件主题";
	static String mailBody="邮件内容,哈哈，傻了吧";
	
	public static void send() throws MessagingException, UnsupportedEncodingException{
		Properties p=new Properties();
		p.put("mail.smtp.host", host);
		p.put("mail.smtp.auth", "false");
		
		Session session=Session.getDefaultInstance(p);
		
		MimeMessage msg=new MimeMessage(session);
		msg.setSubject(mailSubject);
		msg.setText(mailBody);
		Address add=new InternetAddress("741059","我的邮件");
		msg.setFrom(add);
		Address toAdd=new InternetAddress("741059");
		msg.addRecipient(Message.RecipientType.TO, toAdd);
		Transport.send(msg);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException{
		send();
	}
	
	
	
}

