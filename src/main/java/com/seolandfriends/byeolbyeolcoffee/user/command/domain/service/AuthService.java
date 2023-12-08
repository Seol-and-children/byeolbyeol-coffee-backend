package com.seolandfriends.byeolbyeolcoffee.user.command.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seolandfriends.byeolbyeolcoffee.jwt.handler.TokenProvider;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.TokenDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRepository;

@Service
@Slf4j
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final ModelMapper modelMapper;

	public AuthService(UserRepository userRepository
		, PasswordEncoder passwordEncoder
		, TokenProvider tokenProvider
		, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.modelMapper = modelMapper;
	}


	@Transactional
	public Object signup(UserDTO userDTO) {
		log.info("[AuthService] signup Start ==================================");
		log.info("[AuthService] UserDTO {} =======> ", userDTO);

		log.info("[AuthService] Before ModelMapper UserDTO: {}", userDTO);

		User registUser = modelMapper.map(userDTO, User.class);

		log.info("[AuthService] After ModelMapper User: {}", registUser);

		registUser.setUserPassword(passwordEncoder.encode(registUser.getUserPassword()));
		registUser.setStatus(true);
		registUser.setUserRole(2);

		log.info("[AuthService] Before saving User: {}", registUser);

		User result = userRepository.save(registUser);

		log.info("[AuthService] After saving User: {}", result);

		log.info("[AuthService] signup End ==================================");

		return userDTO;
	}


	@Transactional
	public TokenDTO login(UserDTO userDTO) {
		log.info("로그인 시도 - 계정: {}", userDTO.getUserAccount());

		User user = userRepository.findByUserAccount(userDTO.getUserAccount());

		if (user == null) {
			log.warn("로그인 실패: 계정 {}에 해당하는 사용자 없음", userDTO.getUserAccount());
			throw new UsernameNotFoundException("사용자 이름 또는 비밀번호가 잘못되었습니다.");
		} else if (!passwordEncoder.matches(userDTO.getUserPassword(), user.getUserPassword())) {
			log.warn("로그인 실패: 계정 {}의 비밀번호 불일치", userDTO.getUserAccount());
			throw new UsernameNotFoundException("사용자 이름 또는 비밀번호가 잘못되었습니다.");
		} else {
			log.info("로그인 성공 - 사용자: {}", userDTO.getUserAccount());
			return tokenProvider.generateTokenDTO(user);
		}
	}
	@Transactional
	public void logout(String token) {
		tokenProvider.blacklistToken(token);
	}
}