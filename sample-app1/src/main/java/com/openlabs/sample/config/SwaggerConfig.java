package com.openlabs.sample.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.openlabs.sample"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/").enableUrlTemplating(false);

    }
	
	 private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("API")
                .description("[SAMPLE] API")
                .contact(new Contact("SAMPLE Swagger", "https://openlabs.co.kr", "sales@openlabs.co.kr"))
                .version("1.0")
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(false)
                .displayOperationId(true)
                .defaultModelsExpandDepth(999)
                .defaultModelExpandDepth(999)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .filter(true)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl(null)
                .build();
    }
}
