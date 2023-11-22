package com.seolandfriends.byeolbyeolcoffee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

import com.seolandfriends.byeolbyeolcoffee.jwt.filter.CustomAuthenticationFilter;
import com.seolandfriends.byeolbyeolcoffee.jwt.handler.CustomAuthFailureHandler;
import com.seolandfriends.byeolbyeolcoffee.jwt.handler.CustomAuthSuccessHandler;
import com.seolandfriends.byeolbyeolcoffee.jwt.handler.JwtAccessDeniedHandler;
import com.seolandfriends.byeolbyeolcoffee.jwt.handler.JwtAuthenticationEntryPoint;
import com.seolandfriends.byeolbyeolcoffee.jwt.handler.TokenProvider;

@EnableWebSecurity
public class SecurityConfig {

	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final CustomAuthenticationFilter customAuthenticationFilter;
	private final CustomAuthSuccessHandler customAuthSuccessHandler;
	private final CustomAuthFailureHandler customAuthFailureHandler;

	public SecurityConfig(TokenProvider tokenProvider
		, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint
		, JwtAccessDeniedHandler jwtAccessDeniedHandler
		, CustomAuthenticationFilter customAuthenticationFilter
		, CustomAuthSuccessHandler customAuthSuccessHandler
		, CustomAuthFailureHandler customAuthFailureHandler) {
		this.tokenProvider = tokenProvider;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
		this.customAuthenticationFilter = customAuthenticationFilter;
		this.customAuthSuccessHandler = customAuthSuccessHandler;
		this.customAuthFailureHandler = customAuthFailureHandler;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(){
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**",
			"/lib/**", "/productimgs/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
			.exceptionHandling()

			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler)

			.and()
			.authorizeRequests()
			.antMatchers("/").authenticated()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()   // cors를 위해 preflight 요청 처리용 option요청 허용

			.antMatchers("/auth/**").permitAll()
			.antMatchers("/api/v1/products/**").permitAll()
			.antMatchers("/api/v1/reviews/**").permitAll()

			.antMatchers("/api/**").hasAnyRole("USER", "ADMIN")

			.and()

			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
			.cors()

			.and()
			.apply(new JwtSecurityConfig(tokenProvider))

			.and()
			.formLogin().disable()
			.addFilterBefore(customAuthFilter(), UsernamePasswordAuthenticationFilter.class)
			.httpBasic().disable();

		return http.build();
	}

	private Filter customAuthFilter() {
		customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthSuccessHandler);
		customAuthenticationFilter.setAuthenticationFailureHandler(customAuthFailureHandler);
		customAuthenticationFilter.afterPropertiesSet();
		return customAuthenticationFilter;
	}

}