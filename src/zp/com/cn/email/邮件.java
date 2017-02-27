package zp.com.cn.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class 邮件 {
	static String host="smtp.163.com";
	static String username="zptest163@163.com";
	static String password="zptest163";
	static String mailTo="zptest163@163.com";
	String mailFrom="zptest163@163.com";
	static String mailSubject="邮件主题";
	static String mailBody="邮件内容,哈哈，傻了吧";
	
	public static void send() throws MessagingException, UnsupportedEncodingException{
		Properties p=new Properties();
		p.put("mail.smtp.host", host);
		p.put("mail.smtp.auth", "true");
//		p.put("mail.smtp.user",   username);     
//		p.put("mail.smtp.password",   password);    
		
		Session session=Session.getDefaultInstance(p, new MyAuthenticator(username, password));
		
		MimeMessage msg=new MimeMessage(session);
		msg.setSubject(mailSubject);
		msg.setText(mailBody);
		Address add=new InternetAddress(username,"我的邮件");
		msg.setFrom(add);
		Address toAdd=new InternetAddress(mailTo);
		msg.addRecipient(Message.RecipientType.TO, toAdd);
		Transport.send(msg);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException{
		send();
	}
	
	
	
}

