package com.axis.paybooks.request;

public class CompleteDetails {
	
	private String userId;
	
	private String name;
	
	private String email;
	
	private String mobile;
	
	private String gender;
	
	private String department;
	
	private String city;
	
	private String hiredDate;
	
	private String roleId;
	
	private String roleName;
	
	private String resourceId;
	
	private String resourceName;
	
	private Boolean can_add;
	
	private Boolean can_edit;
	
	private Boolean can_delete;
	
	private Boolean can_View;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Boolean getCan_add() {
		return can_add;
	}

	public void setCan_add(Boolean can_add) {
		this.can_add = can_add;
	}

	public Boolean getCan_edit() {
		return can_edit;
	}

	public void setCan_edit(Boolean can_edit) {
		this.can_edit = can_edit;
	}

	public Boolean getCan_delete() {
		return can_delete;
	}

	public void setCan_delete(Boolean can_delete) {
		this.can_delete = can_delete;
	}

	public Boolean getCan_View() {
		return can_View;
	}

	public void setCan_View(Boolean can_View) {
		this.can_View = can_View;
	}

	public CompleteDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompleteDetails(String userId, String name, String email, String mobile, String gender, String department,
			String city, String hiredDate, String roleId, String roleName, String resourceId, String resourceName,
			Boolean can_add, Boolean can_edit, Boolean can_delete, Boolean can_View) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.department = department;
		this.city = city;
		this.hiredDate = hiredDate;
		this.roleId = roleId;
		this.roleName = roleName;
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.can_add = can_add;
		this.can_edit = can_edit;
		this.can_delete = can_delete;
		this.can_View = can_View;
	}
	
	
	

}
