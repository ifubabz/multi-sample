package com.openlabs.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedHeaders("Accept", "Origin", "X-Requested-With", "Content-Type")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowCredentials(true)
        ;
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
