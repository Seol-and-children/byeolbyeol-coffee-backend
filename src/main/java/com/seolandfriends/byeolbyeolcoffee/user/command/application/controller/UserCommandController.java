package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.seolandfriends.byeolbyeolcoffee.common.annotation.UserAuthorize;
import com.seolandfriends.byeolbyeolcoffee.common.response.ApiResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.request.UserUpdateRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.service.UserCommandService;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

/**
 * 회원용 COMMAND API를 처리하는 컨트롤러 클래스입니다.
 * UserAuthorize 어노테이션을 통해 인증된 사용자만 접근할 수 있도록 설정되어 있습니다.
 */
@Tag(name = "회원용 COMMAND API")
@RequiredArgsConstructor
@UserAuthorize
@RestController
@RequestMapping("/users")
public class UserCommandController {
	private final UserCommandService userCommandService;

	/**
	 * 회원 탈퇴를 처리하는 엔드포인트입니다.
	 *
	 * @param user 현재 인증된 사용자 정보
	 * @return ApiResponse 객체로 감싼 회원 탈퇴 결과
	 */
	@Operation(summary = "회원 탈퇴")
	@DeleteMapping
	public ResponseEntity<ApiResponse> deleteUser(@AuthenticationPrincipal User user) {
		userCommandService.deleteUser(UUID.fromString(user.getNickname()));
		return ResponseEntity.ok(ApiResponse.success("성공적으로 탈퇴되었습니다.", null));
	}

	/**
	 * 회원 정보 수정을 처리하는 엔드포인트입니다.
	 *
	 * @param user    현재 인증된 사용자 정보
	 * @param request 회원 정보 수정 요청 정보를 담은 UserUpdateRequest 객체
	 * @return ApiResponse 객체로 감싼 회원 정보 수정 결과
	 */
	@Operation(summary = "회원 정보 수정")
	@PutMapping
	public ResponseEntity<ApiResponse> updateUser(@AuthenticationPrincipal User user, @RequestBody UserUpdateRequest request) {
		return ResponseEntity.ok(ApiResponse.success("성공적으로 수정되었습니다.", userCommandService.updateUser(UUID.fromString(user.getNickname()), request)));
	}
}
