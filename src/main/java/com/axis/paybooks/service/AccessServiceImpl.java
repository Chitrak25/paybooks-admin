package com.axis.paybooks.service;

import java.sql.ResultSet;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.axis.paybooks.model.Resource;
import com.axis.paybooks.model.ResourcePerm;
import com.axis.paybooks.model.Role;
import com.axis.paybooks.model.RolePermission;
import com.axis.paybooks.model.User;
import com.axis.paybooks.model.User_Role;
import com.axis.paybooks.repository.AccessRepository;
import com.axis.paybooks.repository.PermissionRepository;
import com.axis.paybooks.repository.ResourceRepository;
import com.axis.paybooks.repository.RoleRepository;
import com.axis.paybooks.repository.UserRepository;
import com.axis.paybooks.repository.User_RoleRepository;
import com.axis.paybooks.request.CompleteDetails;


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
	
	@Autowired
	ResourceRepository resourceRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	CompleteDetails data=null;

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

	@Override
	public List<Role> getAllRoles() {
		//List<Role> roles=roleRepository.findAll();
		return roleRepository.findAll() ;
	}

	@Override
	public List<Resource> getAllResources() {
		// TODO Auto-generated method stub
		return resourceRepository.findAll();
	}

	@Override
	public CompleteDetails getEmployeeById(String id) {
		List<CompleteDetails> list = jdbcTemplate.query("select user.id as userId ,user.NAME,user.EMAIL_ID,user.mobile,user.gender,user.department,user.city,user.hired_date,user_role.role_id,role.ROLE_NAME,resource_role.resource_id,resource.resource_name, resource_role.can_add,resource_role.can_edit,resource_role.can_delete,resource_role.can_view from resource\r\n"
				+ "inner join resource_role on resource.id=resource_role.resource_id\r\n"
				+ "inner join role on role.id=resource_role.role_id \r\n"
				+ "inner join user_role on role.id=user_role.role_id\r\n"
				+ "inner join user on user.id= user_role.user_id where user.id=?;"
				,  new String[] {id}
				,  (ResultSet rs, int rowNum) -> {
					data = new CompleteDetails();
					data.setUserId(rs.getString("userId"));
					data.setName(rs.getString("name"));
					data.setEmail(rs.getString("email_id"));
					data.setMobile(rs.getString("mobile"));
					data.setGender(rs.getString("gender"));
					data.setDepartment(rs.getString("department"));
					data.setCity(rs.getString("city"));
					data.setHiredDate(rs.getString("hired_date"));
					data.setRoleId(rs.getString("role_id"));
					data.setRoleName(rs.getString("role_name"));
					data.setResourceId(rs.getString("resource_id"));
					data.setResourceName(rs.getString("resource_name"));
					data.setCan_add(rs.getBoolean("can_add"));
					data.setCan_edit(rs.getBoolean("can_edit"));
					data.setCan_delete(rs.getBoolean("can_delete"));
					data.setCan_View(rs.getBoolean("can_view"));
					return data;
					
					
				
				});
		
		return data;
	}
	
	


}
