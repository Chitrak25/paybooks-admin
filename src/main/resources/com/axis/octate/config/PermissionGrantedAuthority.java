package com.axis.octate.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.axis.octate.model.ResourcePermission;

public class PermissionGrantedAuthority implements GrantedAuthority{ 

	public PermissionGrantedAuthority(List<ResourcePermission> resourcePermissions) {
		super();
		this.resourcePermissions = resourcePermissions;
	}

	public List<ResourcePermission> getResourcePermissions() {
		return resourcePermissions;
	}

	public void setResourcePermissions(List<ResourcePermission> resourcePermissions) {
		this.resourcePermissions = resourcePermissions;
	}

	private List<ResourcePermission> resourcePermissions;
	

	@Override
	public String toString() {
		return "PermissionGrantedAuthority [resourcePermissions=" + resourcePermissions + "]";
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
	
		return resourcePermissions.toString();
	}

	


}