package com.openlabs.sample.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CachePeekMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.mapper.cache.IgniteTestMapper;
import com.openlabs.sample.model.Ac0125;
import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.PersonInfo;
import com.openlabs.sample.model.Td1111;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class IgniteTestService {

	@Autowired
	@Qualifier("igniteSessionTemplate")
	private SqlSession sqlSession;
	
	@Autowired 
	Ignite ignite;
	
	public List<PersonInfo> getPersonInfoList() {
		IgniteTestMapper igniteTestMapper = sqlSession.getMapper(IgniteTestMapper.class);
		List<PersonInfo> personInfoList = igniteTestMapper.selectPersonInfoList();
		log.debug("PERSONINFOLIST:{}", personInfoList.size());
		return personInfoList;
	}
	
	public List<Ac0125> getAc0125InfoList(PagingInfo pagingInfo) {
		IgniteTestMapper igniteTestMapper = sqlSession.getMapper(IgniteTestMapper.class);
		List<Ac0125> ac0125InfoList = igniteTestMapper.selectAc0125InfoList(pagingInfo);
		log.debug("AC0125INFOLIST:{}", ac0125InfoList.size());
		return ac0125InfoList;
	}
	
	public List<Td1111> getTd1111InfoList(PagingInfo pagingInfo) {
		IgniteTestMapper igniteTestMapper = sqlSession.getMapper(IgniteTestMapper.class);
		List<Td1111> td1111InfoList = igniteTestMapper.selectTd1111InfoList(pagingInfo);
		log.debug("TD1111INFOLIST:{}", td1111InfoList.size());
		return td1111InfoList;
	}
	
	public String cacheNames() {
		Collection<String> cacheNames = ignite.cacheNames();
		log.debug("CACHENAMES:{}", cacheNames);
		Iterator<String> itr = cacheNames.iterator();
		while(itr.hasNext()) {
			String cacheName = itr.next();
			IgniteCache<Object, Object> cache = ignite.getOrCreateCache(cacheName);
			int size = cache.size(CachePeekMode.PRIMARY);
			log.debug("cacheName:{}, size:{}", cacheName, size);
		}
		return cacheNames.toString();
	}
}
