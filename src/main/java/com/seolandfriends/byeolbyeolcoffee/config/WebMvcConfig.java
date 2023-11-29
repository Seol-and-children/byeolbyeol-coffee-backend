package com.seolandfriends.byeolbyeolcoffee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${image.add-resource-locations}")
	private String ADD_RESOURCE_LOCATION;

	@Value("${image.add-resource-handler}")
	private String ADD_RESOURCE_HANDLER;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler(ADD_RESOURCE_HANDLER)
			.addResourceLocations(ADD_RESOURCE_LOCATION);
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 또는 특정 경로 예: "/recipes/**"
			.allowedOrigins("http://localhost:3000") // 또는 "*"로 모든 출처 허용
			.allowedMethods("GET", "POST", "PUT", "DELETE") // 등등.
			.allowedHeaders("*")
			.allowCredentials(true);
	}
}
