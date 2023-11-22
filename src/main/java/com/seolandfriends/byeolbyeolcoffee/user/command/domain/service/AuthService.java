package com.seolandfriends.byeolbyeolcoffee.user.command.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seolandfriends.byeolbyeolcoffee.exception.DuplicatedMemberEmailException;
import com.seolandfriends.byeolbyeolcoffee.jwt.handler.TokenProvider;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.TokenDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRole;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRepository;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRoleRepository;

@Service
@Slf4j
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final ModelMapper modelMapper;
	private final UserRoleRepository userRoleRepository;

	public AuthService(UserRepository userRepository
		, PasswordEncoder passwordEncoder
		, TokenProvider tokenProvider
		, ModelMapper modelMapper
		, UserRoleRepository userRoleRepository){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.modelMapper = modelMapper;
		this.userRoleRepository = userRoleRepository;
	}

	@Transactional   // DML작업은 Transactional 어노테이션 추가
	public Object signup(UserDTO userDTO) {

		log.info("[AuthService] signup Start ==================================");
		log.info("[AuthService] memberDTO {} =======> ", userDTO);

		/* 이메일 중복 유효성 검사(선택적) */
		if(userRepository.findByUserEmail(userDTO.getUserEmail()) != null){ // 중복된 내용이 있으니 값을 가지고 온 것 (없으면 null)
			log.info("[AuthService] 이메일이 종복됩니다.");
			throw new DuplicatedMemberEmailException("이메일이 중복됩니다.");
		}

		User registUser = modelMapper.map(userDTO, User.class);

		//registUser = registUser.getUserPassword(passwordEncoder.encode(registUser.getUserPassword())).build(); // 평문의 암호문자열을 암호화시켜서 전달
		registUser.setUserPassword(passwordEncoder.encode(registUser.getUserPassword()));

		User result1 = userRepository.save(registUser);    // 반환형이 int값이 아니다.
		log.info("[AuthService] result1 ================== {} ", result1);


		UserRole registUserRole = new UserRole(result1.getUserId(), 2);
		UserRole result2 = userRoleRepository.save(registUserRole);
		log.info("[AuthService] MemberInsert Result {}",
			(result1 != null && result2 != null)? "회원 가입 성공" : "회원 가입 실패");

		log.info("[AuthService] signup End ==================================");

		return userDTO;
	}

	// AuthService에 추가
	@Transactional
	public TokenDTO login(UserDTO userDTO) {
		User user = userRepository.findByUserEmail(userDTO.getUserEmail());
		if (user != null && passwordEncoder.matches(userDTO.getUserPassword(), user.getUserPassword())) {
			return tokenProvider.generateTokenDTO(user); // Generate the token with TokenProvider
		} else {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	@Transactional
	public void logout(String token) {
		tokenProvider.blacklistToken(token);
	}


}