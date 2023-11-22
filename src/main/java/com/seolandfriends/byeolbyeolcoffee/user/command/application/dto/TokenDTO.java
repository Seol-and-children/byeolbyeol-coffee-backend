package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto;

import lombok.*;

/* 토큰 정보 담을 객체*/
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TokenDTO {

	private String grantType;
	private String userNickName;
	private String accessToken;

}