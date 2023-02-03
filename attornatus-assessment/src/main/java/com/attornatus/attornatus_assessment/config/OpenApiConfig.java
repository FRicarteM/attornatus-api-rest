package com.attornatus.attornatus_assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI custumerOpenApi() {
		return new OpenAPI()
				.info(new Info()
					.title("Assessment Attornatus API")
					.version("V.1")
					.description("An API aimed at analyzing the capabilities of developing a back-end "
							+ "code with the JAVA programming language and its Spring Boot Framework. "
							+ "Following good development practices such as Testing, Clean Code, "
							+ "among others.")
					.termsOfService("http://localhost:8080/api/person-address/v1/find-address/fake-link")
					.license(new License()
							.name("Apache 2.0")
							.url("http://localhost:8080/api/person-address/v1/find-address/fake-link")
							)
					);
	}
	
}
