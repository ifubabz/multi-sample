package com.openlabs.sample.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.mapper.cache.PersonMapper;
import com.openlabs.sample.model.PersonInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class CityService {

	@Autowired
	@Qualifier("igniteSessionTemplate")
	private SqlSession sqlSession;
	
	public List<PersonInfo> getPersonInfoList() {
		PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
		List<PersonInfo> personInfoList = personMapper.selectPersonInfoList();
		log.debug("PERSONINFOLIST:{}", personInfoList.size());
		return personInfoList;
	}
}
