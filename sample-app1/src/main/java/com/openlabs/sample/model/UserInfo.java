package com.openlabs.sample.model;

import com.openlabs.sample.common.model.CommonModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserInfo extends CommonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String phoneNumber;
	private String email;
	
	
}
