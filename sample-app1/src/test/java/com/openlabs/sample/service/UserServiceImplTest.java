package com.openlabs.sample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.model.UserInfo;

@Transactional
@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserService userService;
	
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

}
