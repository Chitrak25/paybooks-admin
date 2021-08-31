package com.axis.paybooks.request;

import java.util.Date;

public class OnboardingDocumentCard {
	
	private String userId;
	
	private String hrId;
	
	private String requestFrom;
	
	private Date requestCreatesAt;


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


	
	
	

}
