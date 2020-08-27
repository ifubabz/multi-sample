package com.openlabs.sample.model;

import org.apache.ibatis.session.RowBounds;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class PagingInfo extends RowBounds {

	private int offset;
	private int limit;
	
	public PagingInfo() {
		this.offset = 0;
		this.limit = 1000;
	}
	
	public PagingInfo(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}
}
