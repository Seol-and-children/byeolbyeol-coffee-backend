package com.seolandfriends.byeolbyeolcoffee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 또는 특정 경로 예: "/recipes/**"
			.allowedOrigins("http://localhost:3000") // 또는 "*"로 모든 출처 허용
			.allowedMethods("GET", "POST", "PUT", "DELETE") // 등등.
			.allowedHeaders("*")
			.allowCredentials(true);
	}
}
