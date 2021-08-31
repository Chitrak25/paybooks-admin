package com.axis.octate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.octate.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{

	boolean existsByEmailId(String emailId);
	
	
	
	

}
