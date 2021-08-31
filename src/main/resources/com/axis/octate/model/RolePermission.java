package com.axis.octate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="resource_role")
public class RolePermission {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	@Column(name="resource_id")
	private int resourceId;
	
	@Column(name="role_id")
	private int roleId;
	
	@Column(name="can_add")
	private boolean canAdd;
	
	@Column(name="can_edit")
	private boolean canEdit;
	
	@Column(name="can_view")
	private boolean canView;
	
	@Column(name="can_delete")
	private boolean canDelete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public boolean isCanAdd() {
		return canAdd;
	}

	public void setCanAdd(boolean canAdd) {
		this.canAdd = canAdd;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public boolean isCanView() {
		return canView;
	}

	public void setCanView(boolean canView) {
		this.canView = canView;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public RolePermission(int id, int resourceId, int roleId, boolean canAdd, boolean canEdit, boolean canView,
			boolean canDelete) {
		super();
		this.id = id;
		this.resourceId = resourceId;
		this.roleId = roleId;
		this.canAdd = canAdd;
		this.canEdit = canEdit;
		this.canView = canView;
		this.canDelete = canDelete;
	}

	public RolePermission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
