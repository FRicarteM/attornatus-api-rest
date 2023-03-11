package com.attornatus.attornatus_assessment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Value("${cors.originPattenrs}")
	private String corsOriginPattenrs = "";

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] allowedOrigins = corsOriginPattenrs.split(",");
				registry.addMapping("/**") 
				.allowedMethods("*")
				.allowedOrigins(allowedOrigins)
				.allowCredentials(true);
	}

}
