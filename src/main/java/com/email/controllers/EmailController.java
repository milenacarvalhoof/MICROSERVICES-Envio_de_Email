package com.email.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.dtos.EmailDTO;
import com.email.models.EmailModel;
import com.email.services.EmailService;

@RestController
public class EmailController {
	
	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@GetMapping("/email")
	public ResponseEntity<List<EmailModel>> getEmails(){		
		return ResponseEntity.ok(emailService.listEmails());
	}
	
	@GetMapping("/email/{id}")
	public ResponseEntity<EmailModel> getEmail(@PathVariable Long id){		
		return ResponseEntity.ok(emailService.getEmail(id));
	}
	
	@PostMapping("/sending-email")
	public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO){
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDTO, emailModel);
		emailService.sendEmail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
	}

}
