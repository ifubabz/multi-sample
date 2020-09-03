package com.openlabs.sample.mapper.cache;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.PersonInfo;

import kr.co.openlabs.examples.model.Ac0125;

@Mapper
public interface Ac0125Mapper {

	public List<PersonInfo> selectPersonInfoList();
	
	public List<Ac0125> selectAc0125InfoList(PagingInfo pagingInfo);
	
}
