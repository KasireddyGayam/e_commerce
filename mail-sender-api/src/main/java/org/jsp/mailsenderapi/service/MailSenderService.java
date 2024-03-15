package org.jsp.mailsenderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class MailSenderService 
{
	@Autowired
	private JavaMailSender javaMailSender;
	@Value(value="abcde")
	private String token;
	
	public String sendMail(HttpServletRequest request, String email)
	{
		
		String siteURL=request.getRequestURL().toString();
		String url=siteURL.replace(request.getServletPath(), "/verify");
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setText(url+"?token="+token);
			helper.setSubject("Account Verification");
			javaMailSender.send(message);
			return "Mail has been sent";
		}
		catch (MessagingException e) {
			return "invalid email id";
			// TODO: handle exception
		}
	}
	
	public String verify(String token)
	{
		if(this.token.equals(token))
			return "verification Successfull";
		else
			return "Verification not completed";
	}

}
