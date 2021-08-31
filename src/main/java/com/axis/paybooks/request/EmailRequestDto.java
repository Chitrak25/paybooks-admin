package com.axis.paybooks.request;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;

public class EmailRequestDto {
	
	private String from;
	
	private String subject;
	
	@Email(message = "Invalid Email address")
    private String email;
    
    private String body;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public EmailRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public EmailRequestDto(String subject, @Email(message = "Invalid Email address") String email, String body) {
		super();
		this.subject = subject;
		this.email = email;
		this.body = body;
	}
	
	
	
	
	
	
	
    

}