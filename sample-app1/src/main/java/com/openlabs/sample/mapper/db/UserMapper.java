package com.openlabs.sample.mapper.db;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.UserInfo;

@Mapper
public interface UserMapper {
	
	public List<UserInfo> selectUserInfoList(PagingInfo pagingInfo);
	
	public List<UserInfo> selectUserInfoList(UserInfo userInfo, PagingInfo pagingInfo);
	
	public UserInfo selectUserInfo(String id);
	
	public int insertUserInfo(UserInfo userInfo);
	
	public int updateUserInfo(UserInfo userInfo);

	public int deleteUserInfo(String id);
}
