package com.openlabs.sample.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.dao.UserMapper;
import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.UserInfo;
import com.openlabs.sample.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserInfo> getUserInfoList(PagingInfo pagingInfo) {
		log.debug("PAGINGINFO:{}", pagingInfo);
		List<UserInfo> userInfoList = userMapper.selectUserInfoList(pagingInfo);
		log.debug("USERINFOLIST:{}", userInfoList.size());
		return userInfoList;
	}
	
	@Override
	public List<UserInfo> getUserInfoList(UserInfo userInfo, PagingInfo pagingInfo) {
		log.debug("PAGINGINFO:{}", pagingInfo);
		List<UserInfo> userInfoList = userMapper.selectUserInfoList(userInfo, pagingInfo);
		log.debug("USERINFOLIST:{}", userInfoList.size());
		return userInfoList;
	}
	
	@Override
	public UserInfo getUserById(String id) {
		log.debug("ID:{}", id);
		UserInfo userInfo = userMapper.selectUserInfo(id);
		log.debug("{}", userInfo);
		return userInfo;
	}

	@Override
	public int createUser(UserInfo userInfo) {
		int result = userMapper.insertUserInfo(userInfo);
		return result;
	}

	@Override
	public int modifyUser(UserInfo userInfo) {
		int result = userMapper.updateUserInfo(userInfo);
		return result;
	}

	@Override
	public int removeUser(String id) {
		int result = userMapper.deleteUserInfo(id);
		return result;
	}

}
