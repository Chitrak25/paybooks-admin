package com.axis.octate.service;

import java.util.Optional;

import com.axis.octate.onboarding.model.Documents;
import com.axis.octate.request.OnboardingDocumentCard;

public interface DocumentService {
	
	public String documentRequest(OnboardingDocumentCard card);
	
	public Optional<Documents> getDocumentRequestByUserId(String userid);

}
