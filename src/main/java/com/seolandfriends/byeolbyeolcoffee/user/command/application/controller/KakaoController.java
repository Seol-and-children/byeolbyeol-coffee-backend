package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.seolandfriends.byeolbyeolcoffee.common.ResponseDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.service.KakaoService;

@Controller
public class KakaoController {

	@Autowired
	private KakaoService kakaoService;

	@RequestMapping(value="/login/oauth/kakao/callback", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
		System.out.println("#########" + code);
		String access_Token = kakaoService.getAccessToken(code);
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("###kakaoName#### : " + userInfo.get("userNickName"));
		System.out.println("###kakaoEmail#### : " + userInfo.get("userEmail"));

		HashMap<String, Object> responseData = new HashMap<>();
		responseData.put("access_Token", access_Token);
		responseData.putAll(userInfo);

		//return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "로그인 성공", true, responseData));
		return "/users/myPage";
	}
}
