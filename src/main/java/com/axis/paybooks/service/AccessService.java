package com.axis.paybooks.service;

import java.util.List;

import com.axis.paybooks.model.Resource;
import com.axis.paybooks.model.ResourcePerm;
import com.axis.paybooks.model.Role;
import com.axis.paybooks.model.RolePermission;
import com.axis.paybooks.model.User;
import com.axis.paybooks.model.User_Role;
import com.axis.paybooks.request.CompleteDetails;

public interface AccessService {
	
	public String setRole(User_Role userRole);
	
	public Resource addResource(Resource resource);
	
	public Role addRole(Role role);
	
	public RolePermission grantPermissions(RolePermission rolePermission);
	
	public List<RolePermission>  getPermissionsByRoleId(int roleId);
	
	public Role getRoleByRoleID(int roleID);
	
	public Role getByRoleName(String roleName);
	
	public String updatepermissionsByRoleIDAndResourceId( ResourcePerm resourcePerm);
	
	public List<Role> getAllRoles();
	
	public List<Resource> getAllResources();
	
	public CompleteDetails getEmployeeById(String id);
}
