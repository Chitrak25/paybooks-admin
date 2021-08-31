package com.axis.paybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.paybooks.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{

	boolean existsByEmailId(String emailId);
	
	User findUserByEmailId(String emailId);
	
	
	
	

}
