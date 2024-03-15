package org.jsp.ecommerceapp.service;

import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import static org.jsp.ecommerceapp.util.ApplicationConstants.VERIFY_LINK_MERCHANT;
import static org.jsp.ecommerceapp.util.ApplicationConstants.VERIFY_LINK_USER;

@Service
public class ECommerceApplicationEmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public String welcomeEmail(HttpServletRequest request, Merchant merchant) {
		String siteUrl = request.getRequestURL().toString();
		String url = siteUrl.replace(request.getServletPath(), "");
		String actual_url = url + VERIFY_LINK_MERCHANT + merchant.getToken();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(merchant.getEmail());
			helper.setText(
					"Hi\nClick Here To Activate Your Account\n" + actual_url + "\nInfo:\nIt Works Only One time");
			helper.setSubject("Account ACtivation Mail");
			javaMailSender.send(message);
			return "Verification has been sent";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Verification mail has not been sent to respective mail";
		}
	}

	public String welcomeEmail(HttpServletRequest request, User user) {
		String siteUrl = request.getRequestURL().toString();
		String url = siteUrl.replace(request.getServletPath(), "");
		String actual_url = url + VERIFY_LINK_USER + user.getToken();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(user.getEmail());
			helper.setText(
					"Hi\nClick Here To Activate Your Account\n\n" + actual_url + "\n\nInfo:\n  It Works Only One time");
			helper.setSubject("Account ACtivation Mail");
			javaMailSender.send(message);
			return "Verification mail has been sent";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Verification mail has not been sent to respective mail";
		}
	}

}
