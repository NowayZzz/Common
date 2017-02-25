package com.util.定时任务;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class 定时任务邮件  {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@RequestMapping(value="/sendMail.do",method=RequestMethod.GET)
	public void sendMail(Model model,HttpSession session,
			HttpServletResponse response){
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper ;
		try {
			helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom("spring_mail_test");
			helper.setTo("741059");
			helper.setSubject("spring邮件测试");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "剪刀嘴");
			map.put("age", "26");
			String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "utf-8",  map);
			
			helper.setText(emailText, true);
			
			ClassPathResource image = new ClassPathResource("logo.jpg");
			helper.addInline("logo", image);
			
			javaMailSender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
}
