package com.openlabs.sample.mapper.cache;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.openlabs.sample.model.PersonInfo;

@Mapper
public interface PersonMapper {

	public List<PersonInfo> selectPersonInfoList();
	
}
