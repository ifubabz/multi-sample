package com.openlabs.sample.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ErrorResponse {

	private int status;
	private String message;
	
	@Builder
	public ErrorResponse(ErrorCode errorCode, CommonException cause) {
		this.status = errorCode.getStatus();
		this.message = cause.getMessage();
	}
}
