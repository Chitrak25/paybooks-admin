package com.axis.octate.dao;

import com.axis.octate.model.UserEntity;

public interface OAuthDAOService {

	public UserEntity getUserDetails(String emailId);
}
