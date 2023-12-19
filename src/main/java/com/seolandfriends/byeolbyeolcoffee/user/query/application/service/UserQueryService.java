package com.seolandfriends.byeolbyeolcoffee.user.query.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.query.domain.repository.UserQueryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserQueryService {
	private static final Logger logger = LoggerFactory.getLogger(UserQueryService.class);

	private final UserQueryRepository userQueryRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	public UserQueryService(UserQueryRepository userQueryRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userQueryRepository = userQueryRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDTO selectAnotherUserInfo(Integer userId) {
		log.info("[UserService] selectAnotherUserInfo Start - userId: {}", userId);

		User user = userQueryRepository.findByUserId(userId);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with userId: " + userId);
		}

		log.info("[UserService] selectAnotherUserInfo End - userId: {}", userId);
		return modelMapper.map(user, UserDTO.class);
	}

	public boolean isUserAccountAvailable(String userAccount) {
		boolean isAvailable = !userQueryRepository.existsByUserAccount(userAccount);
		logger.info("Checking if userAccount '{}' is available: {}", userAccount, isAvailable);
		return isAvailable;
	}

	public boolean isUserEmailAvailable(String userEmail) {
		boolean isAvailable = !userQueryRepository.existsByUserEmail(userEmail);
		logger.info("Checking if userEmail '{}' is available: {}", userEmail, isAvailable);
		return isAvailable;
	}

	public boolean isUserNickNameAvailable(String userNickName) {
		boolean isAvailable = !userQueryRepository.existsByUserNickName(userNickName);
		logger.info("Checking if userNickName '{}' is available: {}", userNickName, isAvailable);
		return isAvailable;
	}



	public UserDTO selectMyInfo(String userAccount) {
		log.info("[UserService] selectMyInfo Start - userAccount: {}", userAccount);

		User user = userQueryRepository.findByUserAccount(userAccount);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with Account: " + userAccount);
		}

		log.info("[UserService] selectMyInfo End - userAccount: {}", userAccount);
		return modelMapper.map(user, UserDTO.class);
	}
}
