package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user;

import java.util.Optional;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRefreshToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenDTO {
	private Optional<UserRefreshToken> userRefreshToken;
}
