package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import com.seolandfriends.byeolbyeolcoffee.common.response.ApiResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.login.request.LogInRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.login.response.LogInResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.signup.request.SignUpRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.signup.response.SignUpResponse;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.service.SignCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "회원 가입 및 로그인 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class SignCommandController {
	private final SignCommandService signCommandService;

	@Operation(summary = "회원 가입")
	@PostMapping("/signup")
	public ApiResponse<SignUpRequest> signUp(@RequestBody SignUpRequest request) {
		SignUpResponse response = signCommandService.registerUser(request);
		return ApiResponse.success("성공적으로 가입되었습니다.", request);
	}

	@Operation(summary = "로그인")
	@PostMapping("/login")
	public ApiResponse<LogInResponse> login(@RequestBody LogInRequest request) {
		LogInResponse response = signCommandService.login(request);
		return ApiResponse.success("성공적으로 로그인되었습니다.", response);
	}

	@Operation(summary = "로그아웃")
	@PostMapping("/logout")
	public ApiResponse<Boolean> logout(@RequestParam UUID userId) {
		signCommandService.logout(userId);
		return ApiResponse.success("성공적으로 로그아웃되었습니다.", true);
	}
}
