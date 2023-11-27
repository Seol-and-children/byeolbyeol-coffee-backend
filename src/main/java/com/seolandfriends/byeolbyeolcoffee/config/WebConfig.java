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