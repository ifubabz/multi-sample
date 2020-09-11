package com.openlabs.sample.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.model.PersonInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@SpringBootTest
class IgniteTestServiceTest {

	@Autowired
	private IgniteTestService cityService;
	
	@Test
	void igniteTest() {
		List<PersonInfo> personInfoList = this.cityService.getPersonInfoList();
		log.debug("PERSONINFOLIST:{}", personInfoList);
	}
	
//	@Test
//	void igniteCache() {
//		String cacheName = this.cityService.cacheNames();
//		log.debug("CACHENAME:{}", cacheName);
//	}
}
