package com.openlabs.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.dao.UserMapper;
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
