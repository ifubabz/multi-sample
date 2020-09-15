package com.openlabs.sample.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openlabs.sample.common.RetrybleRestTemplate;
import com.openlabs.sample.common.secure.EncryptorFactory;
import com.openlabs.sample.common.secure.EncryptorFactory.ENCRYPT_TYPE;
import com.openlabs.sample.exception.CommonException;
import com.openlabs.sample.exception.ErrorCode;
import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.UserInfo;
import com.openlabs.sample.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "사용자관리")
@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RetrybleRestTemplate restTemplate;
	
	@ApiOperation("현재 시간 출력")
	@GetMapping(path = "/now")
	public String now() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		log.debug("NOW:{}", now);
		return now;
	}
	
	@ApiOperation("재시도 테스트")
	@GetMapping(path = "/retry")
	public ResponseEntity<String> retry() {
		return restTemplate.getForEntity("http://localhost:8080/users/now", String.class);
	}
	
	@ApiOperation("재시도 테스트")
	@GetMapping(path = "/retry1")
	public ResponseEntity<String> retry1() {
		return restTemplate.getForEntity("http://localhost:8080/user/now", String.class);
	}
	
	@ApiOperation("암호화")
	@GetMapping(path = "/enc")
	public ResponseEntity<String> enc(@RequestBody UserInfo userInfo) {
		String result = EncryptorFactory.getInstance().getEncryptor().encrypt(userInfo.getId());
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation("암호화")
	@GetMapping(path = "/enc2")
	public ResponseEntity<String> enc2(@RequestBody UserInfo userInfo) {
		String result = EncryptorFactory.getInstance().getEncryptor(ENCRYPT_TYPE.JCE).encrypt(userInfo.getId());
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation("사용자 정보 목록")
	@GetMapping(path = "/")
	public ResponseEntity<List<UserInfo>> getUserList(@ModelAttribute PagingInfo pagingInfo) {
		log.debug("pagingInfo:{}", pagingInfo);
		List<UserInfo> userInfoList = userService.getUserInfoList(pagingInfo);
		log.debug("SIZE:{}", userInfoList.size());
		return ResponseEntity.ok(userInfoList);
	}
	
	@ApiOperation("사용자 정보 조회")
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserInfo> getUserById(@PathVariable String id) {
		log.debug("ID:{}", id);
		UserInfo userInfo = userService.getUserById(id);
		log.debug("{}", userInfo);
		return ResponseEntity.ok(userInfo);
	}
	
	@ApiOperation("사용자 정보 등록")
	@PostMapping
	public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo) {
		log.debug("USERINFO:{}", userInfo);
		userService.createUser(userInfo);
		
		UserInfo result = userService.getUserById(userInfo.getId());
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation("사용자 정보 수정")
	@PutMapping
	public ResponseEntity<UserInfo> modifyUser(@RequestBody UserInfo userInfo) {
		log.debug("USERINFO:{}", userInfo);
		userService.modifyUser(userInfo);
		
		UserInfo result = userService.getUserById(userInfo.getId());
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation("사용자 정보 삭제")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> removeUser(@PathVariable String id) {
		log.debug("ID:{}", id);
		userService.removeUser(id);
		
		return ResponseEntity.ok(id);
	}
	
	@ApiOperation("오류 테스트")
	@GetMapping(path = "/err")
	public ResponseEntity<UserInfo> err() {
		throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
	}
	
	@ApiOperation("사용자 정보 등록")
	@PostMapping(path = "/sql/err")
	public ResponseEntity<UserInfo> transactionTest(@RequestBody UserInfo userInfo) {
		log.debug("USERINFO:{}", userInfo);
		userService.transactionTest(userInfo);
		
		UserInfo result = userService.getUserById(userInfo.getId());
		return ResponseEntity.ok(result);
	}
	
}
