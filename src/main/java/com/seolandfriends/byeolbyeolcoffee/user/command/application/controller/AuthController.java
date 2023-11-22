package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.common.ResponseDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.TokenDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService){
		this.authService = authService;
	}

	@ApiOperation(value = "회원 가입 요청", notes = "회원 가입이 진행됩니다.", tags = {"AuthController"})
	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@RequestBody UserDTO userDTO){
		return ResponseEntity
			.ok()
			.body(new ResponseDTO(HttpStatus.CREATED, "회원가입 성공", authService.signup(userDTO)));

	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO userDTO) {
		TokenDTO tokenDTO = authService.login(userDTO); // TokenDTO 객체를 반환받음
		String accessToken = tokenDTO.getAccessToken(); // TokenDTO에서 accessToken 필드 값을 가져옴
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공", accessToken));
	}

	@PostMapping("/logout")
	public ResponseEntity<ResponseDTO> logout() {
		/* 로그아웃 로직 블랙리스트 처리 */
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "로그아웃 되었습니다.", null));
	}

}