package com.seolandfriends.byeolbyeolcoffee.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.seolandfriends.byeolbyeolcoffee.jwt.filter.HeaderFilter;
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private final HeaderFilter headerFilter;

	public WebConfig(HeaderFilter headerFilter) {
		this.headerFilter = headerFilter;
	}

	@Bean
	public FilterRegistrationBean<HeaderFilter> getFilterRegistrationBean() {
		FilterRegistrationBean<HeaderFilter> registrationBean = new FilterRegistrationBean<>(headerFilter);
		registrationBean.setOrder(Integer.MIN_VALUE);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}