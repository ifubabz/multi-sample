package com.openlabs.sample.service;

import java.util.List;

import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.UserInfo;

public interface UserService {

	public List<UserInfo> getUserInfoList(PagingInfo pagingInfo);
	
	public List<UserInfo> getUserInfoList(UserInfo userInfo, PagingInfo pagingInfo);
	
	public UserInfo getUserById(String id);
	
	public int createUser(UserInfo userInfo);
	
	public int modifyUser(UserInfo userInfo);
	
	public int removeUser(String id);
}
