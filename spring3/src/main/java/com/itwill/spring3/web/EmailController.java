package com.itwill.spring3.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring3.service.EmailService;
import com.itwill.spring3.service.EmailServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmailController {
	@Autowired
	private EmailService emailService;
	private EmailServiceImpl emailServiceImpl;
	
	
	@PostMapping("/emailConfirm")
	public String emailConfirm(@RequestParam String email) throws Exception {

		log.info("email={}", email);
		
	  String confirm = emailService.sendSimpleMessage(email);
	  log.info("emailServiceImpl.ePw={}",emailServiceImpl.ePw);

	  return confirm;
	}
}
