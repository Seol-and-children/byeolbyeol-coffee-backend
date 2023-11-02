package com.seolandfriends.byeolbyeolcoffee.user.command.domain.service;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

	private final UserRepository userRepository;

	@Autowired
	public RegistrationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void registerUser(String account, String password, String email, String nickname) {
		// 회원가입 로직 및 유효성 검사
		User newUser = User.builder()
			.account(account)
			.password(password)
			.email(email)
			.nickname(nickname)
			// 다른 필드 설정
			.build();

		userRepository.save(newUser);
	}
}
