package com.seolandfriends.byeolbyeolcoffee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.seolandfriends.byeolbyeolcoffee")
public class WebConfig implements WebMvcConfigurer {

	@Value("${image.add-resource-locations}")
	private String ADD_RESOURCE_LOCATION;

	@Value("${image.add-resource-handler}")
	private String ADD_RESOURCE_HANDLER;
}