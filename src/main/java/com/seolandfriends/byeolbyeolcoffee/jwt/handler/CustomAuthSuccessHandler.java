package com.seolandfriends.byeolbyeolcoffee.jwt.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.seolandfriends.byeolbyeolcoffee.common.AuthConstants;
import com.seolandfriends.byeolbyeolcoffee.common.ResponseDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.TokenDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final TokenProvider tokenProvider;
	private final ModelMapper modelMapper;

	public CustomAuthSuccessHandler(TokenProvider tokenProvider, ModelMapper modelMapper) {
		this.tokenProvider = tokenProvider;
		this.modelMapper = modelMapper;
	}

	@Override
	@ResponseBody
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		User user = modelMapper.map(authentication.getPrincipal(), User.class);
		TokenDTO tokenDTO = tokenProvider.generateTokenDTO(user);

		ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "로그인 성공", tokenDTO);
		response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + tokenDTO.getAccessToken());

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		ObjectMapper objectMapper = new ObjectMapper();

		out.println(objectMapper.writeValueAsString(responseDTO));
		out.flush();
		out.close();
	}
}