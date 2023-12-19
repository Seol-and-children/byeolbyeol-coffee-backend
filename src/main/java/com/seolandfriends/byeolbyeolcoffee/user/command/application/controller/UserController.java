package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seolandfriends.byeolbyeolcoffee.common.ResponseDTO;
import com.seolandfriends.byeolbyeolcoffee.common.UserPasswordCheckRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.service.UserService;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private final UserService userService;

	public UserController(UserService userService)	 {
		this.userService = userService;
	}

	@PutMapping("/{userAccount}")
	public ResponseEntity<ResponseDTO> updateUserInfo(@PathVariable String userAccount, @RequestBody UserDTO userDTO) {
		UserDTO updatedUserDTO = userService.updateUserInfo(userAccount, userDTO);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "회원 정보 수정 성공", true, updatedUserDTO));
	}

	@PutMapping("/{userAccount}/bio")
	public ResponseEntity<ResponseDTO> updateUserBio(@PathVariable String userAccount, @RequestBody String userBio) {
		UserDTO updatedUserDTO = userService.updateUserBio(userAccount, userBio);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "회원 자기소개 수정 성공", true, updatedUserDTO));
	}

	@PostMapping("/checkUserPassword")
	public ResponseEntity<?> checkUserPassword(@RequestBody UserPasswordCheckRequest request) {
		logger.info("Received checkUserPassword request for user ID: {}, password: {}", request.getUserId(), request.getUserPassword());
		boolean isPasswordCorrect = userService.checkUserPassword(request.getUserId(), request.getUserPassword());
		return ResponseEntity.ok(isPasswordCorrect);
	}


	@ApiOperation(value = "회원 탈퇴 요청", notes = "사용자 계정을 삭제합니다.", tags = { "UserController" })
	@DeleteMapping("/{userAccount}")
	public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String userAccount) {
		userService.deleteUserByAccount(userAccount);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "회원 탈퇴 성공", true, null));
	}
}
