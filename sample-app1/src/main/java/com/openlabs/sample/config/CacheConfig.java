package com.openlabs.sample.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CacheConfig {

	@Bean
	public Ignite ignite() {
		Ignite ignite = Ignition.start("default-config.xml");
		return ignite;
	}
	
}
