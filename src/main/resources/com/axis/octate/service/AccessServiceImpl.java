package com.axis.octate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.octate.model.Resource;
import com.axis.octate.model.ResourcePerm;
import com.axis.octate.model.Role;
import com.axis.octate.model.RolePermission;
import com.axis.octate.model.User;
import com.axis.octate.model.User_Role;
import com.axis.octate.repository.AccessRepository;
import com.axis.octate.repository.PermissionRepository;
import com.axis.octate.repository.RoleRepository;
import com.axis.octate.repository.UserRepository;
import com.axis.octate.repository.User_RoleRepository;

@Service
public class AccessServiceImpl implements AccessService {
	
	@Autowired
	AccessRepository accessRepository;

	@Autowired
	PermissionRepository permissionRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	User_RoleRepository userRoleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	

	@Override
	public String setRole(User_Role userRole) {
		// TODO Auto-generated method stub
		userRoleRepository.save(userRole);
		return "users role is set";
	}

	public Resource addResource(Resource resource) {

		return accessRepository.save(resource) ;
	}

	public RolePermission grantPermissions(RolePermission rolePermission) {

		return permissionRepository.save(rolePermission);

	}


	public Role addRole(Role role) {

		return roleRepository.save(role) ;
	}

	@Override
	public List<RolePermission> getPermissionsByRoleId(int roleId) {

		return permissionRepository.findByroleId( roleId);
	}


	@Override
	public Role getRoleByRoleID(int roleID) {

		return roleRepository.findByroleID(roleID);
	}


	@Override
	public Role getByRoleName(String roleName) {
		

		return roleRepository.findByroleName(roleName);
	}

	@Override
	public String updatepermissionsByRoleIDAndResourceId(ResourcePerm resourcePerm) {

		Role role=getByRoleName(resourcePerm.getRoleName());

		System.out.println(resourcePerm.getPermissionList());

		for(RolePermission p:resourcePerm.getPermissionList()) {

			p.setRoleId(role.getRoleID());
		}

		System.out.println(resourcePerm);

		for(RolePermission p:resourcePerm.getPermissionList()) {

			RolePermission rp=permissionRepository.findByRoleIdAndResourceId(p.getRoleId(),p.getResourceId());

			rp.setCanView(p.isCanView());
			rp.setCanEdit(p.isCanEdit());
			rp.setCanAdd(p.isCanAdd());
			rp.setCanDelete(p.isCanDelete());
			System.out.println(rp);
			permissionRepository.save(rp);

		}
		return "success";
	}
	
	
	
	public List<User> findAllUser() {
		return  userRepository.findAll();
	}

	
	
	


}
