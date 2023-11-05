package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user;

import java.util.Optional;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 사용자 정보를 담는 DTO(Data Transfer Object) 클래스입니다.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoDTO {

	// Optional을 사용하여 User 엔터티를 감싼 객체를 나타내는 변수입니다.
	private Optional<User> userInfo;
}
