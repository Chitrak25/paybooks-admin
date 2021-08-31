package com.axis.octate.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.axis.octate.config.PermissionGrantedAuthority;
import com.axis.octate.model.Resource;
import com.axis.octate.model.ResourcePermission;
import com.axis.octate.model.UserEntity;

@Repository
public class OAuthDAOServiceImpl implements OAuthDAOService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public UserEntity getUserDetails(String emailId) {
		
		Collection<PermissionGrantedAuthority> permissionAuthoritiesList = new ArrayList<>();
		UserEntity userEntity =null;
		
		List<UserEntity> list = jdbcTemplate.query("SELECT * FROM USER WHERE EMAIL_ID=?", new String[] { emailId },
				(ResultSet rs, int rowNum) -> {
						UserEntity user = new UserEntity();
						user.setEmailId(emailId);
						user.setId(rs.getString("ID"));
						user.setName(rs.getString("NAME"));
						user.setPassword(rs.getString("PASSWORD"));
						return user;
				});

		if(!list.isEmpty()) {
			
			userEntity = list.get(0);
			
			
			List<ResourcePermission> resourceRoleList= jdbcTemplate.query("select r_role.id,r_role.resource_id,r.resource_name,r.api_url,r_role.role_id, r_role.can_add, r_role.can_edit, r_role.can_view, r_role.can_delete from resource r \r\n" +
			"inner join resource_role r_role on r.id=r_role.resource_id\r\n" +
			"inner join role ro on ro.id=r_role.role_id\r\n" + 
			"inner join user_role ur on ro.id=ur.role_id\r\n" +
			"inner join user u on u.id=ur.user_id\r\n" +
			" where u.email_id=?", new String[] { userEntity.getEmailId() },
			  (ResultSet rs, int rowNum) -> {
				ResourcePermission resourcepermission = new ResourcePermission();
				Resource resource=new Resource();
				resource.setResourceName(rs.getString("resource_name"));
				resource.setApiUrl(rs.getString("api_url"));
				resourcepermission.setResource(resource);
				resourcepermission.setId(rs.getInt("id"));
				resourcepermission.setResource_id(rs.getInt("resource_id"));
				resourcepermission.setRole_id(rs.getInt("role_id"));
				resourcepermission.setCan_add(rs.getBoolean("can_add"));
				resourcepermission.setCan_view(rs.getBoolean("can_view"));
				resourcepermission.setCan_edit(rs.getBoolean("can_edit"));
				resourcepermission.setCan_delete(rs.getBoolean("can_delete"));
				return resourcepermission;
			});
			
			for( ResourcePermission resourcepermission : resourceRoleList) {
				System.out.println(resourcepermission.getRole_id());
				System.out.println(resourcepermission.getResource().getResourceName());
				System.out.println(resourcepermission.getResource().getApiUrl());
				System.out.println(resourcepermission.isCan_add());
				System.out.println(resourcepermission.isCan_view());
				System.out.println(resourcepermission.isCan_edit());
				System.out.println(resourcepermission.isCan_delete());
				//System.out.println(userEntity.getName());
				
			}
		
			  if (resourceRoleList != null && !resourceRoleList.isEmpty()) { 
					
					 PermissionGrantedAuthority grantedAuthority = new PermissionGrantedAuthority(resourceRoleList);
					  
					  permissionAuthoritiesList.add(grantedAuthority);
					  
					  
			  }
		
			  userEntity.setPermissionGrantedAuthorities(permissionAuthoritiesList);
			}
		return userEntity;
	}
}
