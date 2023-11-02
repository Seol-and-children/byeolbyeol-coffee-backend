package com.seolandfriends.byeolbyeolcoffee.user.command.domain.service;

import java.util.Optional;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	private final UserRepository userRepository;

	@Autowired
	public LoginService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean authenticateUser(String account, String password) {
		Optional<User> userOptional = userRepository.findByAccount(account);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getPassword().equals(password)) {
				// 로그인 성공
				return true;
			}
		}
		// 로그인 실패
		return false;
	}
}
