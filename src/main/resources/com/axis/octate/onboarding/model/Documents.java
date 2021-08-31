package com.axis.octate.onboarding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Onborading_Documents")
public class Documents {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	@Column(name="emp_id")
	private String userId;
	
	@Column(name="hr_id")
	private String hrId;
	
	@Column(name="educational_documents")
	private String educationalDocument;
	
	@Column(name="address_proof")
	private String addressProof;
	
	@Column(name="request_from")
	private String requestFrom;
	
	@Column(name="request_created")
	private Date requestCreatesAt;
	
	@Column(name="document_submitted")
	private Date documentSubmittedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHrId() {
		return hrId;
	}

	public void setHrId(String hrId) {
		this.hrId = hrId;
	}

	public String getEducationalDocument() {
		return educationalDocument;
	}

	public void setEducationalDocument(String educationalDocument) {
		this.educationalDocument = educationalDocument;
	}

	public String getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public Date getRequestCreatesAt() {
		return requestCreatesAt;
	}

	public void setRequestCreatesAt(Date requestCreatesAt) {
		this.requestCreatesAt = requestCreatesAt;
	}

	public Date getDocumentSubmittedAt() {
		return documentSubmittedAt;
	}

	public void setDocumentSubmittedAt(Date documentSubmittedAt) {
		this.documentSubmittedAt = documentSubmittedAt;
	}

	public Documents(String userId, String hrId, String requestFrom, Date requestCreatesAt) {
		super();
		this.userId = userId;
		this.hrId = hrId;
		this.requestFrom = requestFrom;
		this.requestCreatesAt = requestCreatesAt;
	}
	
	
	
	

	
	
	
	
	
	
	

}
