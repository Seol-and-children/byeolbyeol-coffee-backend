package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto;

import java.util.UUID;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class TokenDTO {

	private String grantType;
	private int userId;
	private String accessToken;

	public TokenDTO(String tokenType, int userId, String accessToken) {
	}
}