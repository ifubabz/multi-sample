package com.openlabs.sample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.UserInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	void getUserList() {
		int pageNo = 0;
		int rowPerPage = 10;
		PagingInfo pagingInfo = new PagingInfo(pageNo, rowPerPage);
		List<UserInfo> userList = userService.getUserInfoList(pagingInfo);
		int size = userList.size();
		log.debug("USERLIST:{}", userList);
		assertEquals(rowPerPage, size);
	}
	
	@Test
	void getUserFilterList() {
		int pageNo = 0;
		int rowPerPage = 10;
		PagingInfo pagingInfo = new PagingInfo(pageNo, rowPerPage);
		UserInfo param = UserInfo.builder().name("12345").build();
		List<UserInfo> userList = userService.getUserInfoList(param, pagingInfo);
		int size = userList.size();
		log.debug("USERLIST:{}", userList);
		assertEquals(rowPerPage, size);
	}
	
	@Test
	void getUserById() {
		UserInfo userInfo = userService.getUserById("A001");
		assertEquals("A001", userInfo.getId());
		assertEquals("ejpark@openlabs.co.kr", userInfo.getEmail());
	}
	
	@Rollback(true)
	@Test
	void createUser() {
		UserInfo userInfo = UserInfo.builder().id("A003").name("어피치").phoneNumber("111-1111-1111").email("apeach@openlabs.co.kr").build(); 
		userService.createUser(userInfo);
		
		UserInfo result = userService.getUserById("A003");
		assertEquals("A003", result.getId());
		assertEquals("어피치", result.getName());
		assertEquals("111-1111-1111", result.getPhoneNumber());
		assertEquals("apeach@openlabs.co.kr", result.getEmail());
	}

	@ParameterizedTest
	@ValueSource(strings = {"A0000000000000001", "A0000000000000002"})
	void parameteriedGetUserById(String id) {
		UserInfo userInfo = userService.getUserById(id);
		assertEquals(id, userInfo.getId());
	}
	
	@ParameterizedTest
	@MethodSource("userInfo")
	void parameteriedCreateUser(UserInfo param) {
		userService.createUser(param);
		
		UserInfo result = userService.getUserById(param.getId());
		assertEquals(param.getId(), result.getId());
		assertEquals(param.getName(), result.getName());
		assertEquals(param.getPhoneNumber(), result.getPhoneNumber());
		assertEquals(param.getEmail(), result.getEmail());
	}
	
	private static Stream<UserInfo> userInfo() {
	    return Stream.of(
	    		UserInfo.builder().id("A003").name("어피치").phoneNumber("111-1111-1111").email("apeach@openlabs.co.kr").build()
	    		,UserInfo.builder().id("A004").name("뮤지").phoneNumber("222-2222-2222").email("muzi@openlabs.co.kr").build()
	    		,UserInfo.builder().id("A005").name("튜브").phoneNumber("333-3333-3333").email("tube@openlabs.co.kr").build()
	    );
	}

}
