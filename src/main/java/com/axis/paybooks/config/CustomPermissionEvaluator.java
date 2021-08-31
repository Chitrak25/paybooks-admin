package com.axis.paybooks.config;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.axis.paybooks.constant.PermissionURLConstants;
import com.axis.paybooks.model.ResourcePermission;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
	
	@Override
	public boolean hasPermission(Authentication authentication, Object accessType, Object permission) {
	
		if (authentication != null && accessType instanceof String) {
			
			Collection<PermissionGrantedAuthority> authorities = 
			(Collection<PermissionGrantedAuthority>) authentication.getAuthorities();

			
			boolean hasAPIAccess = false;
			
	for(PermissionGrantedAuthority authority : authorities ) {
		
			for(ResourcePermission resourcePermission :authority.getResourcePermissions()) {
				
				hasAPIAccess = resourcePermission.getResource().getApiUrl().equals(accessType);
			
				 if(hasAPIAccess) {

					 boolean isValid = validateScope(resourcePermission,permission);
					    
					 if(isValid) {  
 						 return true;
					 }
				}
				
			}
		}
			
	 }
		
	return false;
	
	}
	
	
	private boolean validateScope(ResourcePermission resourcePermission, Object permission) {
		
		
		if(PermissionURLConstants.VIEW.equalsIgnoreCase((String) permission)  ) {
		
		return hasReadPermission(resourcePermission, permission);
		}
		else if (PermissionURLConstants.ADD.equalsIgnoreCase((String) permission)) {
			
			return  hasAddPermission(resourcePermission, permission);
		}
		else if (PermissionURLConstants.EDIT.equalsIgnoreCase((String) permission)) {
		
			return  hasEditPermission(resourcePermission, permission);
		}
		else if(PermissionURLConstants.DELETE.equalsIgnoreCase((String) permission)) {
			
			return hasDeletePermission(resourcePermission, permission);
		}
		
		return false;
	}
	
	
	private boolean hasReadPermission(ResourcePermission resourcePermission, Object permission) {

		if(resourcePermission.isCan_view() ) {
			return true;
		}
		
		return false;
	}
	
	
	
	private boolean hasAddPermission(ResourcePermission resourcePermission, Object permission) {

		if(resourcePermission.isCan_add() ) {
			return true;
		}
		
		return false;
	}
	
	
	private boolean hasEditPermission(ResourcePermission resourcePermission, Object permission) {

		if(resourcePermission.isCan_edit() ) {
			return true;
		}
		
		return false;
	}
	
	private boolean hasDeletePermission(ResourcePermission resourcePermission, Object permission) {

		if(resourcePermission.isCan_delete() ) {
			return true;
		}
		
		return false;
	}
	
	
	
	@Override
	public boolean hasPermission(Authentication authentication, Serializable serializable, String targetType,
			Object permission) {
		return false;
	}
}







