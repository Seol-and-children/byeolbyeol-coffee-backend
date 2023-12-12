package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.common.ResponseDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.TokenDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRepository;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.service.AuthService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/users")
public class AuthController {

	private final AuthService authService;
	private final UserRepository userRepository;

	public AuthController(AuthService authService, UserRepository userRepository){
		this.authService = authService;
		this.userRepository = userRepository;
	}

	@ApiOperation(value = "회원 가입 요청", notes = "회원 가입이 진행됩니다.", tags = {"AuthController"})
	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@RequestBody UserDTO userDTO){

		Object signupResult = authService.signup(userDTO);
		boolean signupSuccess = signupResult != null;

		return ResponseEntity
			.ok()
			.body(new ResponseDTO(HttpStatus.CREATED, "회원가입 성공", signupSuccess, signupResult));
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO userDTO) {
		TokenDTO tokenDTO = authService.login(userDTO);
		String accessToken = tokenDTO.getAccessToken();

		User user = userRepository.findByUserAccount(userDTO.getUserAccount());
		if (user == null) {
			// 적절한 예외 처리
		}
		String userNickName = user.getUserNickName();
		String userAccount = user.getUserAccount();

		// 로그인한 사용자의 계정 정보를 포함시킵니다.
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("accessToken", accessToken);
		responseData.put("userNickName", userNickName);
		responseData.put("userAccount", userAccount);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공", true, responseData));
	}

	@PostMapping("/logout")
	public ResponseEntity<ResponseDTO> logout() {
		/* 로그아웃 로직 블랙리스트 처리 */
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "로그아웃 되었습니다.",true, null));
	}

}