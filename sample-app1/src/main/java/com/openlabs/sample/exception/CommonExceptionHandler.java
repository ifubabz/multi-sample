package com.openlabs.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(CommonException.class)
	protected ResponseEntity<ErrorResponse> handleCommonException(final CommonException e) {
		log.debug("ERROR:{}", e.getErrorCode());
	    final ErrorResponse response = ErrorResponse.builder().errorCode(e.getErrorCode()).cause(e).build();
	    log.debug("response:{}", response);
	    return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
}
