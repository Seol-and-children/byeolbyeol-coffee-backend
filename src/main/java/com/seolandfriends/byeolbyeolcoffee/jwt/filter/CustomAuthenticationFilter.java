package com.seolandfriends.byeolbyeolcoffee.jwt.filter;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seolandfriends.byeolbyeolcoffee.jwt.handler.CustomAuthenticationProvider;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private CustomAuthenticationProvider customAuthenticationProvider;

	public CustomAuthenticationFilter(CustomAuthenticationProvider customAuthenticationProvider) {
		super.setAuthenticationManager(new ProviderManager(customAuthenticationProvider));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authRequest;

		try {
			authRequest = getAuthRequest(request);
			setDetails(request, authRequest);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

		UserDTO user = objectMapper.readValue(request.getInputStream(), UserDTO.class);

		return new UsernamePasswordAuthenticationToken(user.getUserAccount(), user.getUserPassword(), user.getAuthorities());
	}

}