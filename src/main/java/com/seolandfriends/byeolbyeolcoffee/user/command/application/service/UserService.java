package com.seolandfriends.byeolbyeolcoffee.user.command.application.service;

import java.util.Optional;

import com.seolandfriends.byeolbyeolcoffee.exception.InvalidPasswordException;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public UserDTO updateUserInfo(String userAccount, UserDTO newUserDTO) {
		log.info("[사용자 서비스] 회원 정보 수정 시작 - 계정: {}", userAccount);

		// 사용자 조회
		User user = userRepository.findByUserAccount(userAccount);
		if (user == null) {
			log.warn("[사용자 서비스] 회원 정보 수정 - 계정 {}에 해당하는 사용자 없음", userAccount);
			throw new UsernameNotFoundException("계정 " + userAccount + "에 해당하는 사용자를 찾을 수 없습니다.");
		} else {
			log.info("[사용자 서비스] 회원 정보 수정 - 사용자 찾음: {}", user);
		}

		// 현재 비밀번호 확인
		if (!passwordEncoder.matches(newUserDTO.getCurrentPassword(), user.getUserPassword())) {
			log.warn("[사용자 서비스] 회원 정보 수정 - 잘못된 현재 비밀번호, 계정: {}", userAccount);
			throw new InvalidPasswordException("현재 비밀번호가 잘못되었습니다.");
		}

		// 닉네임 업데이트
		user.setUserNickName(newUserDTO.getUserNickName());


		// 새 비밀번호 설정 (있는 경우)
		if (newUserDTO.getNewPassword() != null && !newUserDTO.getNewPassword().isEmpty()) {
			user.setUserPassword(passwordEncoder.encode(newUserDTO.getNewPassword()));
		}

		userRepository.save(user);
		log.info("[사용자 서비스] 회원 정보 수정 - 사용자 정보 업데이트 완료, 계정: {}", userAccount);

		// DTO 변환 및 반환
		UserDTO updatedUserDTO = modelMapper.map(user, UserDTO.class);
		log.info("[사용자 서비스] 회원 정보 수정 종료 - 업데이트된 UserDTO: {}", updatedUserDTO);

		return updatedUserDTO;
	}

	@Transactional
	public UserDTO updateUserBio(String userAccount, String newBio) {
		log.info("[사용자 서비스] 회원 자기소개 수정 시작 - 계정: {}", userAccount);

		// 사용자 조회
		User user = userRepository.findByUserAccount(userAccount);
		if (user == null) {
			log.warn("[사용자 서비스] 회원 자기소개 수정 - 계정 {}에 해당하는 사용자 없음", userAccount);
			throw new UsernameNotFoundException("계정 " + userAccount + "에 해당하는 사용자를 찾을 수 없습니다.");
		}

		// 자기소개 업데이트
		user.setUserBio(newBio);
		userRepository.save(user);
		log.info("[사용자 서비스] 회원 자기소개 수정 - 사용자 자기소개 업데이트 완료, 계정: {}", userAccount);

		// DTO 변환 및 반환
		UserDTO updatedUserDTO = modelMapper.map(user, UserDTO.class);
		log.info("[사용자 서비스] 회원 자기소개 수정 종료 - 업데이트된 UserDTO: {}", updatedUserDTO);

		return updatedUserDTO;
	}

	public boolean checkUserPassword(Integer userId, String userPassword) {
		logger.info("Checking password for user ID: {}", userId);

		User user = userRepository.findByUserId(userId);
		if (user == null) {
			logger.warn("User not found for ID: {}", userId);
			throw new UsernameNotFoundException("User not found");
		}

		boolean passwordMatches = passwordEncoder.matches(userPassword, user.getUserPassword());
		logger.info("Password match for user ID {}: {}", userId, passwordMatches);

		return passwordMatches;
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
