package com.openlabs.sample.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CommonException extends RuntimeException {

	private static final long serialVersionUID = -8043346470647082229L;
	
	private ErrorCode errorCode;

	@Builder
	public CommonException(ErrorCode errorCode){
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
	
	@Builder
	public CommonException(ErrorCode errorCode, String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	@Builder
	public CommonException(ErrorCode errorCode, String message, Throwable cause){
		super(message, cause);
		this.errorCode = errorCode;
	}

}
