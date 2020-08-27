package com.openlabs.sample.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openlabs.sample.exception.CommonExceptionHandler;
import com.openlabs.sample.exception.ErrorCode;
import com.openlabs.sample.model.UserInfo;
import com.openlabs.sample.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@SpringBootTest
class UserControllerTest {

	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	private void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
			.setControllerAdvice(new CommonExceptionHandler())
			.alwaysDo(print())
			.build();
	}

	@Test
	void getUser() throws Exception {
		UserInfo userInfo = UserInfo.builder().id("A001").name("박응준").phoneNumber("010-6478-9411").email("ejpark@openlabs.co.kr").build();

		when(userService.getUserById("A001")).thenReturn(userInfo);
		this.mockMvc.perform(get("/users/A001"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().json("{\"id\":\"A001\",\"name\":\"박응준\",\"phoneNumber\":\"010-6478-9411\",\"email\":\"ejpark@openlabs.co.kr\"}"))
					;
	}
	
	@Test
	void createUser() throws Exception {
		UserInfo userInfo = UserInfo.builder().id("A003").name("어피치").phoneNumber("111-1111-1111").email("apeach@openlabs.co.kr").build();
		
		when(userService.getUserById("A003")).thenReturn(userInfo);
		when(userService.createUser(userInfo)).thenReturn(1);

		String content = objectMapper.writeValueAsString(userInfo);
		log.debug("CONTENT:{}", content);
		
		this.mockMvc.perform(post("/users")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":\"A003\",\"name\":\"어피치\",\"phoneNumber\":\"111-1111-1111\",\"email\":\"apeach@openlabs.co.kr\"}"))
				;
	}
	
	@Test
	void error() throws Exception {
		this.mockMvc.perform(get("/users/err"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(content().string(containsString(ErrorCode.INVALID_INPUT_VALUE.getMessage())))
		;
	}

}

