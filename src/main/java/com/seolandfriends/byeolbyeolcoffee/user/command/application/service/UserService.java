package com.seolandfriends.byeolbyeolcoffee.user.command.application.service;

import java.util.Optional;
import java.util.UUID;

import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
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

	@Transactional
	public UserDTO updateUserInfo(String userAccount, UserDTO newUserDTO) {
		log.info("[UserService] updateUserInfo Start - userAccount: {}", userAccount);

		User user = userRepository.findByUserAccount(userAccount);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with Account: " + userAccount);
		}

		user.setUserNickName(newUserDTO.getUserNickName());
		userRepository.save(user);

		log.info("[UserService] updateUserInfo End - userAccount: {}", userAccount);
		return modelMapper.map(user, UserDTO.class);
	}

	@Transactional
	public void deleteUserByAccount(String userAccount) {
		log.info("[UserService] deleteUserByAccount Start - userAccount: {}", userAccount);

		User user = Optional.ofNullable(userRepository.findByUserAccount(userAccount))
			.orElseThrow(() -> new UsernameNotFoundException("User not found with account: " + userAccount));

		userRepository.delete(user);

		log.info("[UserService] deleteUserByAccount End - userAccount: {}", userAccount);
	}
}
