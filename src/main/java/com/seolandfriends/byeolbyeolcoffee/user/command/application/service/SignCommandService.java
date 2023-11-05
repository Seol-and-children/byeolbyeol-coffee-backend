package com.seolandfriends.byeolbyeolcoffee.user.command.application.service;

import java.util.UUID;

import com.seolandfriends.byeolbyeolcoffee.configuration.jwt.TokenProvider;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.login.request.LogInRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.login.response.LogInResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.signup.request.SignUpRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.signup.response.SignUpResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRefreshToken;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserCommandRefreshTokenRepository;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserCommandRepository;
import com.seolandfriends.byeolbyeolcoffee.user.query.application.service.SignQueryService;
import com.seolandfriends.byeolbyeolcoffee.user.query.application.service.UserQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 회원가입, 로그인, 로그아웃 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@RequiredArgsConstructor
@Service
public class SignCommandService {

	private final UserCommandRepository userCommandRepository;
	private final UserCommandRefreshTokenRepository userCommandRefreshTokenRepository;
	private final UserQueryService userQueryService;
	private final SignQueryService signQueryService;
	private final TokenProvider tokenProvider;
	private final PasswordEncoder encoder;

	/**
	 * 사용자 회원가입을 처리하는 메서드입니다.
	 *
	 * @param request 회원가입 요청 데이터를 담고 있는 SignUpRequest 객체
	 * @return 회원가입 결과를 담은 SignUpResponse 객체
	 */
	@Transactional
	public SignUpResponse registerUser(SignUpRequest request) {
		// 회원 정보를 저장하고 반환합니다.
		User user = userCommandRepository.save(User.from(request, encoder));

		try {
			// 데이터베이스에 변경사항을 즉시 반영합니다.
			userCommandRepository.flush();
		} catch (DataIntegrityViolationException e) {
			// 이미 사용중인 아이디일 경우 예외를 던집니다.
			throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
		}

		// 회원가입 결과를 SignUpResponse 객체로 변환하여 반환합니다.
		return SignUpResponse.from(user);
	}

	/**
	 * 사용자 로그인을 처리하는 메서드입니다.
	 *
	 * @param request 로그인 요청 데이터를 담고 있는 SignInRequest 객체
	 * @return 로그인 결과를 담은 SignInResponse 객체
	 */
	@Transactional
	public LogInResponse login(LogInRequest request) {
		// 로그인 요청에 해당하는 사용자 정보를 조회합니다.
		User user = signQueryService.findUserInfoByAccount(request).getUserInfo()
			.filter(it -> encoder.matches(request.getPassword(), it.getPassword()))
			.orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

		// Access Token과 Refresh Token을 생성합니다.
		String accessToken = tokenProvider.createAccessToken(String.format("%s:%s", user.getId(), user.getRole()));
		String refreshToken = tokenProvider.createRefreshToken();

		// 사용자의 리프레시 토큰이 이미 존재한다면 갱신하고, 없다면 새로운 리프레시 토큰을 추가합니다.
		userQueryService.findRefreshTokenById(user).getUserRefreshToken()
			.ifPresentOrElse(
				it -> it.updateRefreshToken(refreshToken),
				() -> userCommandRefreshTokenRepository.save(new UserRefreshToken(user, refreshToken))
			);

		// 로그인 결과를 담은 SignInResponse 객체를 생성하여 반환합니다.
		return new LogInResponse(user.getNickname(), user.getEmail(), user.getRole(), accessToken, refreshToken);
	}

	/**
	 * 사용자 로그아웃을 처리하는 메서드입니다.
	 *
	 * @param userId 로그아웃을 요청한 사용자의 ID
	 */
	@Transactional
	public void logout(UUID userId) {
		// 리프레시 토큰을 저장소에서 제거하여 사용자의 토큰을 무효화합니다.
		userCommandRefreshTokenRepository.deleteByUserId(userId);
		// 세션을 폐기하는 등의 추가 작업이 필요하다면 여기에 추가할 수 있습니다.
	}
}
