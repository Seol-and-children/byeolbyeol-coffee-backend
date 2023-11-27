package com.seolandfriends.byeolbyeolcoffee.user.command.application.service;

import java.util.Optional;

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
		log.info("[MemberService]  selectMyInfo   Start =============== ");

		User user = userRepository.findByUserAccount(userAccount);
		log.info("[MemberService]  {} =============== ", user);
		log.info("[MemberService]  selectMyInfo   End =============== ");
		return modelMapper.map(user, UserDTO.class);
	}

	@Transactional
	public UserDTO updateUserInfo(String userAccount, UserDTO newUserDTO) {
		User user = userRepository.findByUserAccount(userAccount);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		user.setUserNickName(newUserDTO.getUserNickName());
		userRepository.save(user);
		return modelMapper.map(user, UserDTO.class);
	}

	@Transactional
	public void deleteUserByAccount(String userAccount) {
		log.info("[UserService] deleteUserByAccount Start =============== ");

		User user = Optional.ofNullable(userRepository.findByUserAccount(userAccount))
			.orElseThrow(() -> new UsernameNotFoundException("계정 " + userAccount + "를 가진 사용자를 찾을 수 없습니다."));

		userRepository.delete(user);

		log.info("[UserService] deleteUserByAccount End ================= ");
	}

}