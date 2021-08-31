package com.axis.octate.model;

public class ResourcePermission {

	private int id ;
	private int resource_id;
	private int role_id;
	private Resource resource;
	private boolean can_add;
	private boolean can_edit;
	private boolean can_view;
	private boolean can_delete;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResource_id() {
		return resource_id;
	}
	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public boolean isCan_add() {
		return can_add;
	}
	public void setCan_add(boolean can_add) {
		this.can_add = can_add;
	}
	public boolean isCan_edit() {
		return can_edit;
	}
	public void setCan_edit(boolean can_edit) {
		this.can_edit = can_edit;
	}
	public boolean isCan_view() {
		return can_view;
	}
	public void setCan_view(boolean can_view) {
		this.can_view = can_view;
	}
	public boolean isCan_delete() {
		return can_delete;
	}
	public void setCan_delete(boolean can_delete) {
		this.can_delete = can_delete;
	}
	@Override
	public String toString() {
		String roll =String.valueOf(role_id);
		return   roll;
	}
	
	
	
	
}
