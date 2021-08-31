package com.axis.paybooks.service;

import java.util.Optional;

import com.axis.paybooks.onboarding.model.Documents;
import com.axis.paybooks.request.OnboardingDocumentCard;

public interface DocumentService {
	
	public String documentRequest(OnboardingDocumentCard card);
	
	public Optional<Documents> getDocumentRequestByUserId(String userid);

}
