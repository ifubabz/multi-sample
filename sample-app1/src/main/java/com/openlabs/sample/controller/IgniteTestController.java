package com.openlabs.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openlabs.sample.model.Ac0125;
import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.Td1111;
import com.openlabs.sample.service.IgniteTestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "캐시 테스트")
@Slf4j
@RequestMapping("/cache")
@RestController
public class IgniteTestController {

	@Autowired
	private IgniteTestService igniteTestService;
	
	@ApiOperation("Ignite AC0125")
	@GetMapping(path = "/ac0125")
	public ResponseEntity<List<Ac0125>> ac0125(@ModelAttribute PagingInfo pagingInfo) {
		log.debug("pagingInfo:{}", pagingInfo);
		List<Ac0125> ac0125InfoList = igniteTestService.getAc0125InfoList(pagingInfo);
		log.debug("SIZE:{}", ac0125InfoList.size());
		return ResponseEntity.ok(ac0125InfoList);
	}
	
	@ApiOperation("Ignite AC0125")
	@PutMapping(path = "/ac0125")
	public ResponseEntity<Ac0125> modifyTest(@RequestBody Ac0125 param) {
		log.debug("PARAM:{}", param);
		int result = igniteTestService.modifyAc0125Info(param);
		log.debug("result:{}", result);
		Ac0125 ac0125 = igniteTestService.getAc0125Info(param);
		log.debug("AC0125:{}", ac0125);
		return ResponseEntity.ok(ac0125);
	}
	
//	@ApiOperation("Ignite AC0125")
//	@PutMapping(path = "/ac0125/err")
//	public ResponseEntity<kr.co.openlabs.examples.model.Ac0125> modifyErrTest(@RequestBody Ac0125 param) {
//		log.debug("PARAM:{}", param);
//		kr.co.openlabs.examples.model.Ac0125 ac0125 = igniteTestService.transactionTest(param);
//		log.debug("AC0125:{}", ac0125);
//		return ResponseEntity.ok(ac0125);
//	}
	
	@ApiOperation("Ignite AC0125")
	@PutMapping(path = "/ac0125/sql/err")
	public ResponseEntity<Ac0125> modifySqlErrTest(@RequestBody Ac0125 param) {
		log.debug("PARAM:{}", param);
		Ac0125 ac0125 = igniteTestService.sqlTransactionTest(param);
		log.debug("AC0125:{}", ac0125);
		return ResponseEntity.ok(ac0125);
	}
	
	@ApiOperation("Ignite TD1111")
	@GetMapping(path = "/td1111")
	public ResponseEntity<List<Td1111>> td1111(@ModelAttribute PagingInfo pagingInfo) {
		log.debug("pagingInfo:{}", pagingInfo);
		List<Td1111> td1111InfoList = igniteTestService.getTd1111InfoList(pagingInfo);
		log.debug("SIZE:{}", td1111InfoList.size());
		return ResponseEntity.ok(td1111InfoList);
	}
}
