package com.axis.paybooks.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.paybooks.onboarding.model.Documents;
import com.axis.paybooks.repository.DocumentRepository;
import com.axis.paybooks.request.OnboardingDocumentCard;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	DocumentRepository documentRepository;

	@Override
	public String documentRequest(OnboardingDocumentCard card) {
		
		Date date = new Date();
		card.setRequestCreatesAt(date);
		
		Documents doc = new Documents(card.getUserId(),card.getHrId(),card.getRequestFrom(),card.getRequestCreatesAt());
		
		documentRepository.save(doc);
		
		return "document request sent";
	}

	@Override
	public Optional<Documents> getDocumentRequestByUserId(String userid) {
		
		
		Optional<Documents> card = documentRepository.findById(userid);

		return card;
	}
	

	
	


}
