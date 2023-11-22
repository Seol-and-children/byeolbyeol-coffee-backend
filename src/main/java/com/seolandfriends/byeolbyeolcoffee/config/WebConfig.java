package com.seolandfriends.byeolbyeolcoffee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.seolandfriends.byeolbyeolcoffee.jwt.filter.HeaderFilter;

@Configuration
@ComponentScan(basePackages = "com.seolandfriends.byeolbyeolcoffee")
public class WebConfig implements WebMvcConfigurer {

	@Value("${image.add-resource-locations}")
	private String ADD_RESOURCE_LOCATION;

	@Value("${image.add-resource-handler}")
	private String ADD_RESOURCE_HANDLER;

	private final HeaderFilter headerFilter;

	public WebConfig(HeaderFilter headerFilter) {
		this.headerFilter = headerFilter;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler(ADD_RESOURCE_HANDLER)
			.addResourceLocations(ADD_RESOURCE_LOCATION);
	}

	@Bean
	public FilterRegistrationBean<HeaderFilter> getFilterRegistrationBean() {
		FilterRegistrationBean<HeaderFilter> registrationBean = new FilterRegistrationBean<>(headerFilter);
		registrationBean.setOrder(Integer.MIN_VALUE);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}