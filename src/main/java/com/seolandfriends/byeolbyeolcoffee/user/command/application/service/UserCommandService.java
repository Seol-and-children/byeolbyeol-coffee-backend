package com.seolandfriends.byeolbyeolcoffee.user.command.application.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seolandfriends.byeolbyeolcoffee.common.response.ApiResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.request.UserUpdateRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.response.UserDeleteResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.response.UserUpdateResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserCommandRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserCommandService {
	private final UserCommandRepository userCommandRepository;
	private final PasswordEncoder encoder;

	/**
	 * 회원 정보를 수정하는 서비스 메서드입니다.
	 *
	 * @param id      수정할 회원의 고유 식별자(UUID)
	 * @param request 수정할 회원 정보를 담고 있는 UserUpdateRequest 객체
	 * @return ResponseEntity 객체로 감싼 회원 정보 수정 결과
	 */
	@Transactional
	public ResponseEntity<ApiResponse<UserUpdateResponse>> updateUser(UUID id, UserUpdateRequest request) {
		return userCommandRepository.findById(id)
			.map(user -> {
				if (encoder.matches(request.getPassword(), user.getPassword())) {
					user.update(request, encoder);
					userCommandRepository.save(user);
					UserUpdateResponse response = UserUpdateResponse.of(true, user);
					// ApiResponse의 제네릭 타입을 명시하여 호출
					return ResponseEntity.ok(ApiResponse.<UserUpdateResponse>success("회원 정보가 성공적으로 수정되었습니다.", response));
				} else {
					throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
				}
			})
			.orElseGet(() -> {
				// ApiResponse의 제네릭 타입을 명시하여 호출
				return ResponseEntity.ok(
					(ApiResponse<UserUpdateResponse>)ApiResponse.<UserUpdateResponse>error("해당 아이디로 회원을 찾을 수 없습니다."));
			});
	}


	/**
	 * 회원을 탈퇴하는 서비스 메서드입니다.
	 *
	 * @param id 탈퇴할 회원의 고유 식별자(UUID)
	 * @return ResponseEntity 객체로 감싼 회원 탈퇴 결과
	 */
	@Transactional
	public ResponseEntity<ApiResponse<UserDeleteResponse>> deleteUser(UUID id) {
		if (userCommandRepository.existsById(id)) {
			userCommandRepository.deleteById(id);
			return ResponseEntity.ok(ApiResponse.success("회원이 성공적으로 탈퇴되었습니다.", new UserDeleteResponse(true)));
		} else {
			return ResponseEntity.ok(ApiResponse.success("해당 아이디로 회원을 찾을 수 없습니다.", new UserDeleteResponse(false)));
		}
	}
}
