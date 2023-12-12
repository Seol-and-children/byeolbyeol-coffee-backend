package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto;


import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class TokenDTO {

	private String grantType;
	private int userId;
	private String accessToken;

	public TokenDTO(String grantType, int userId, String accessToken) {
		this.grantType = grantType;
		this.userId = userId;
		this.accessToken = accessToken;
	}
}