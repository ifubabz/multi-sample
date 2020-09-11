package com.openlabs.sample.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteTransactions;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openlabs.sample.exception.CommonException;
import com.openlabs.sample.exception.ErrorCode;
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
	private Ignite ignite;
	
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

	public Ac0125 getAc0125Info(Ac0125 ac0125) {
		IgniteTestMapper igniteTestMapper = sqlSession.getMapper(IgniteTestMapper.class);
		Ac0125 ac0125Info = igniteTestMapper.selectAc0125Info(ac0125);
		log.debug("AC0125INFO:{}", ac0125Info);
		return ac0125Info;
	}
	
	public int modifyAc0125Info(Ac0125 ac0125) {
		IgniteTestMapper igniteTestMapper = sqlSession.getMapper(IgniteTestMapper.class);
		return igniteTestMapper.updateAc0125Info(ac0125);
	}
	
	public List<Td1111> getTd1111InfoList(PagingInfo pagingInfo) {
		IgniteTestMapper igniteTestMapper = sqlSession.getMapper(IgniteTestMapper.class);
		List<Td1111> td1111InfoList = igniteTestMapper.selectTd1111InfoList(pagingInfo);
		log.debug("TD1111INFOLIST:{}", td1111InfoList.size());
		return td1111InfoList;
	}
	
	public Ac0125 sqlTransactionTest(Ac0125 ac0125) {
		IgniteTestMapper igniteTestMapper = sqlSession.getMapper(IgniteTestMapper.class);
		try {
			Connection conn = sqlSession.getConnection();
			log.debug("getAutoCommit:{}", conn.getAutoCommit());
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int result = igniteTestMapper.updateAc0125Info(ac0125);
		log.debug("RESULT:{}", result);
		Ac0125 ac0125Info = igniteTestMapper.selectAc0125Info(ac0125);
		log.debug("AC0125INFO:{}", ac0125Info);

		throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
	}
	
	public kr.co.openlabs.examples.model.Ac0125 transactionTest(Ac0125 param) {
		IgniteTransactions transactions = ignite.transactions();
		IgniteCache<String, kr.co.openlabs.examples.model.Ac0125> ac0125Cache = ignite.getOrCreateCache("Ac0125Cache");
		kr.co.openlabs.examples.model.Ac0125 ac0125Info = null;
		try (Transaction tx = transactions.txStart()) {
			String key = param.getAcno();
			ac0125Info = ac0125Cache.get(key);
			log.debug("AC0125INFO:{}", ac0125Info);
			ac0125Info.setTgIvsrYn(param.getTgIvsrYn());
			ac0125Cache.put(key, ac0125Info);
			log.debug("RESULT:{}", ac0125Cache.get(key));

			if(true) {
				throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);			
			}
			
			tx.commit();
		}
		return ac0125Info;
	}
	
	public String cacheNames() {
		Collection<String> cacheNames = this.ignite.cacheNames();
		log.debug("CACHENAMES:{}", cacheNames);
		Iterator<String> itr = cacheNames.iterator();
		while(itr.hasNext()) {
			String cacheName = itr.next();
			IgniteCache<Object, Object> cache = this.ignite.getOrCreateCache(cacheName);
			int size = cache.size(CachePeekMode.PRIMARY);
			log.debug("cacheName:{}, size:{}", cacheName, size);
		}
		return cacheNames.toString();
	}
}
