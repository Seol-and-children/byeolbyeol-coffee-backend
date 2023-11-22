package com.seolandfriends.byeolbyeolcoffee.jwt.filter;

import com.seolandfriends.byeolbyeolcoffee.common.AuthConstants;
import com.seolandfriends.byeolbyeolcoffee.jwt.handler.TokenProvider;
import com.seolandfriends.byeolbyeolcoffee.util.TokenUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	private final TokenProvider tokenProvider;

	public JwtFilter(TokenProvider tokenProvider){
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String jwt = resolveToken(request);

		if(StringUtils.hasText(jwt) && TokenUtils.isValidToken(jwt)){
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String resolveToken(HttpServletRequest request){

		String bearerToken = request.getHeader(AuthConstants.AUTH_HEADER.toLowerCase());
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(AuthConstants.TOKEN_TYPE)){
			return TokenUtils.splitHeader(bearerToken);
		}
		return null;
	}

}