package com.seolandfriends.byeolbyeolcoffee.config;


import com.seolandfriends.byeolbyeolcoffee.jwt.handler.TokenProvider;
import com.seolandfriends.byeolbyeolcoffee.jwt.filter.JwtFilter;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final TokenProvider tokenProvider;

	public JwtSecurityConfig(TokenProvider tokenProvider){
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		JwtFilter customFilter = new JwtFilter(tokenProvider);    // JwtFilter를 jwt 패키지에 추가
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class); // JwtFilter를 FilterChain 상에서 추가
	}
}