package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto;

import lombok.*;

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