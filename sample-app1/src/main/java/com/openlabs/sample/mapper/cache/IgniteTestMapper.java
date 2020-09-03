package com.openlabs.sample.mapper.cache;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.openlabs.sample.model.Ac0125;
import com.openlabs.sample.model.PagingInfo;
import com.openlabs.sample.model.PersonInfo;
import com.openlabs.sample.model.Td1111;

@Mapper
public interface IgniteTestMapper {

	public List<PersonInfo> selectPersonInfoList();
	
	public List<Ac0125> selectAc0125InfoList(PagingInfo pagingInfo);
	
	public List<Td1111> selectTd1111InfoList(PagingInfo pagingInfo);
	
}
