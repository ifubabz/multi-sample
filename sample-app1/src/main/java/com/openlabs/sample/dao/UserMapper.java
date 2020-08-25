package com.openlabs.sample.dao;

import org.apache.ibatis.annotations.Mapper;

import com.openlabs.sample.model.UserInfo;

@Mapper
public interface UserMapper {
	
	public UserInfo selectUserInfo(String id);
	
	public int insertUserInfo(UserInfo userInfo);
	
	public int updateUserInfo(UserInfo userInfo);

	public int deleteUserInfo(String id);
}
