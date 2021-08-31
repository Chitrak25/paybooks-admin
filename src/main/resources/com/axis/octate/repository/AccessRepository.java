package com.axis.octate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.octate.model.Resource;
import com.axis.octate.model.RolePermission;

@Repository
public interface AccessRepository extends JpaRepository<Resource,Integer> {

	RolePermission save(RolePermission rolePermission);

}
