package com.axis.paybooks.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.axis.paybooks.config.PermissionGrantedAuthority;

public class UserEntity {
	
	
	private String id;
	private String name;
	private String emailId;
	private String password;
	private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

	private Collection<PermissionGrantedAuthority> permissionGrantedAuthorities = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
		return grantedAuthoritiesList;
	}

	public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
		this.grantedAuthoritiesList = grantedAuthoritiesList;
	}

	

	public Collection<PermissionGrantedAuthority> getPermissionGrantedAuthorities() {
		return permissionGrantedAuthorities;
	}

	public void setPermissionGrantedAuthorities(Collection<PermissionGrantedAuthority> permissionGrantedAuthorities) {
		this.permissionGrantedAuthorities = permissionGrantedAuthorities;
	}

	public UserEntity(String name, String emailId, String password) {
		super();
		
		this.name = name;
		this.emailId = emailId;
		this.password = password;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

}
