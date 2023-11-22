package com.seolandfriends.byeolbyeolcoffee.jwt.handler;


import org.json.simple.JSONObject;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		JSONObject jsonObject;
		String failMsg;

		if(exception instanceof AuthenticationServiceException) {
			failMsg = "존재하지 않는 사용자입니다.";
		} else if(exception instanceof BadCredentialsException) {
			failMsg = "아이디 또는 비밀번호가 틀립니다.";
		} else if(exception instanceof LockedException) {
			failMsg = "잠긴 계정입니다. 관리자에게 문의하세요.";
		} else if(exception instanceof DisabledException) {
			failMsg = "비활성화된 계정입니다. 관리자에게 문의하세요.";
		} else if(exception instanceof AccountExpiredException) {
			failMsg = "만료된 계정입니다. 관리자에게 문의하세요.";
		} else if(exception instanceof CredentialsExpiredException) {
			failMsg = "비밀번호가 만료되었습니다.";
		} else {
			failMsg = "예상치 못한 오류입니다. 관리자에게 문의하세요.";
		}

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("failType", failMsg);

		jsonObject = new JSONObject();
		jsonObject.putAll(resultMap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		out.println(jsonObject);
		out.flush();
		out.close();
	}
}