package com.openlabs.sample.service;

import com.openlabs.sample.model.UserInfo;

public interface UserService {

	public UserInfo getUserById(String id);
	
	public int createUser(UserInfo userInfo);
	
	public int modifyUser(UserInfo userInfo);
	
	public int removeUser(String id);
	
}
