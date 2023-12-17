package com.seolandfriends.byeolbyeolcoffee.user.query.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserQueryService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	public UserQueryService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDTO selectAnotherUserInfo(Integer userId) {
		log.info("[UserService] selectAnotherUserInfo Start - userId: {}", userId);

		User user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with userId: " + userId);
		}

		log.info("[UserService] selectAnotherUserInfo End - userId: {}", userId);
		return modelMapper.map(user, UserDTO.class);
	}

	public UserDTO selectMyInfo(String userAccount) {
		log.info("[UserService] selectMyInfo Start - userAccount: {}", userAccount);

		User user = userRepository.findByUserAccount(userAccount);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with Account: " + userAccount);
		}

		log.info("[UserService] selectMyInfo End - userAccount: {}", userAccount);
		return modelMapper.map(user, UserDTO.class);
	}
}
