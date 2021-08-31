	package com.axis.octate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="role")
public class Role {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int roleID;
	
	@Column(name="ROLE_NAME")
	private String roleName;
	
	/*
	 * @Column(name="ROLE_DESCRIPTION") private String roleDescription;
	 */

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/*
	 * public String getRoleDescription() { return roleDescription; }
	 * 
	 * public void setRoleDescription(String roleDescription) { this.roleDescription
	 * = roleDescription; }
	 */

	public Role(int roleID, String roleName) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
		//this.roleDescription = roleDescription;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
