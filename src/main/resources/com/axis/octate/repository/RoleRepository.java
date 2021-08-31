package com.axis.octate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.octate.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

	public Role findByroleID(int roleId);


	 public Role findByroleName(String roleName);


}
