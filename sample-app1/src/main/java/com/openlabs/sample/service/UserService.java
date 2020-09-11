package com.openlabs.sample.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.exception.CommonException;
import com.openlabs.sample.exception.ErrorCode;
import com.openlabs.sample.mapper.db.UserMapper;
import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.UserInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class UserService {

	@Autowired
	@Qualifier("primarySessionTemplate")
	private SqlSession sqlSession;
	
	@Autowired
	UserMapper userMapper;
	
	public List<UserInfo> getUserInfoList(PagingInfo pagingInfo) {
		log.debug("PAGINGINFO:{}", pagingInfo);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<UserInfo> userInfoList = userMapper.selectUserInfoList(pagingInfo);
		log.debug("USERINFOLIST:{}", userInfoList.size());
		return userInfoList;
	}
	
	public List<UserInfo> getUserInfoList(UserInfo userInfo, PagingInfo pagingInfo) {
		log.debug("PAGINGINFO:{}", pagingInfo);
		List<UserInfo> userInfoList = userMapper.selectUserInfoList(userInfo, pagingInfo);
		log.debug("USERINFOLIST:{}", userInfoList.size());
		return userInfoList;
	}
	
	public UserInfo getUserById(String id) {
		log.debug("ID:{}", id);
		UserInfo userInfo = userMapper.selectUserInfo(id);
		log.debug("{}", userInfo);
		return userInfo;
	}

	public int createUser(UserInfo userInfo) {
		int result = userMapper.insertUserInfo(userInfo);
		return result;
	}

	public int transactionTest(UserInfo userInfo) {
		int result = userMapper.insertUserInfo(userInfo);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
	}
	
	public int modifyUser(UserInfo userInfo) {
		int result = userMapper.updateUserInfo(userInfo);
		return result;
	}

	public int removeUser(String id) {
		int result = userMapper.deleteUserInfo(id);
		return result;
	}

}
