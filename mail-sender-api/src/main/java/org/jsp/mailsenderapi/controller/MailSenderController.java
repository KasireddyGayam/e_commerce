package org.jsp.mailsenderapi.controller;

import org.jsp.mailsenderapi.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MailSenderController {
	@Autowired
	private MailSenderService service;
	

	@PostMapping("/send-mail")
	public String sendMail(HttpServletRequest request,@RequestParam String email) {
		
		return service.sendMail(request, email);
	}

	@GetMapping("/verify")
	public String verify(@RequestParam String token) {
		return service.verify(token);
	}
}
