package com.openlabs.sample.config;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.ibatis.session.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ConfigurationProperties(prefix = IgniteProperties.IGNITE_PREFIX)
public class IgniteProperties {

	public static final String IGNITE_PREFIX = "ignite";

	private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

	private String[] mapperLocations;

	@NestedConfigurationProperty
	private Configuration configuration;

	private String configFile;
	
	public Resource[] resolveMapperLocations() {
		return Stream.of(Optional.ofNullable(this.mapperLocations).orElse(new String[0]))
				.flatMap(location -> Stream.of(getResources(location))).toArray(Resource[]::new);
	}

	private Resource[] getResources(String location) {
		try {
			return resourceResolver.getResources(location);
		} catch (IOException e) {
			return new Resource[0];
		}
	}
}
