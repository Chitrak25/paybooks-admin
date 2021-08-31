package com.axis.octate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.octate.request.OnboardingDocumentCard;
import com.axis.octate.service.DocumentServiceImpl;

@RestController
@RequestMapping("/api/onboarding")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RequestOnboardingDocumentsController {
	
	@Autowired
	DocumentServiceImpl documentService;
	
	@PostMapping("/hr/documentrequest")
	public ResponseEntity<String> documentReq(@Valid @RequestBody OnboardingDocumentCard card){
		return new ResponseEntity<String>(documentService.documentRequest(card),HttpStatus.OK);
	}
	
	
	
	

}
