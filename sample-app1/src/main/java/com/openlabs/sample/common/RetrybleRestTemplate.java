package com.openlabs.sample.common;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetrybleRestTemplate extends RestTemplate {

	@Autowired
	private RetryTemplate retryTemplate;
	
	protected <T> T doExecute(URI url, @Nullable HttpMethod method, @Nullable RequestCallback requestCallback,
			@Nullable ResponseExtractor<T> responseExtractor) throws RestClientException {

		return retryTemplate.execute(context -> {
			log.debug("RetryContext:{}", context);
			return super.doExecute(url, method, requestCallback, responseExtractor);
		});
		
//		return retryTemplate.execute(new RetryCallback<T, RestClientException>() {
//			
//			@Override
//			public T doWithRetry(RetryContext context) throws RestClientException {
//				log.debug("RetryContext:{}", context);
//				return retryExecute(url, method, requestCallback, responseExtractor);
//			}
//		});
	}
	
//	private <T> T retryExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) {
//		return super.doExecute(url, method, requestCallback, responseExtractor);
//	}
}
