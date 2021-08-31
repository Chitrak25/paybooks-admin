package com.axis.octate.model;

import java.util.List;

public class ResourcePerm {
	
	private String roleName;
	
	
    List<RolePermission>  permissionList;


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public List<RolePermission> getPermissionList() {
		return permissionList;
	}


	public void setPermissionList(List<RolePermission> permissionList) {
		this.permissionList = permissionList;
	}


	public ResourcePerm() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ResourcePerm(String roleName, List<RolePermission> permissionList) {
		super();
		this.roleName = roleName;
		this.permissionList = permissionList;
	}
    
    


	
}
