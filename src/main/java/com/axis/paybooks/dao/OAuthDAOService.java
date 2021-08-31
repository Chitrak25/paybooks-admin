package com.axis.paybooks.dao;

import com.axis.paybooks.model.UserEntity;

public interface OAuthDAOService {

	public UserEntity getUserDetails(String emailId);
}
