package com.axis.octate.request;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;

public class EmailRequestDto {
	
	private String from;
	
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
	public EmailRequestDto( @Email(message = "Invalid Email address") String email, String body) {
		super();
		
		this.email = email;
		this.body = body;
	}
	public EmailRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
    

}
