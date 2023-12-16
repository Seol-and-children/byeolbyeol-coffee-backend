package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import java.util.UUID;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.seolandfriends.byeolbyeolcoffee.common.ResponseDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.service.UserService;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	private final UserService userService;

	public UserController(UserService userService)	 {
		this.userService = userService;
	}

	@ApiOperation(value = "회원 조회 요청", notes = "회원 한명이 조회됩니다.", tags = { "UserController" })
	@GetMapping("/{userAccount}")
	public ResponseEntity<ResponseDTO> selectMyUserInfo(@PathVariable String userAccount){
		log.info("[UserController] selectMyUserInfo Start - userAccount: {}", userAccount);

		UserDTO userDTO = userService.selectMyInfo(userAccount);

		log.info("[UserController] selectMyUserInfo End - userAccount: {}", userAccount);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", true, userDTO));
	}

	@GetMapping("/other/{userId}")
	public ResponseEntity<ResponseDTO> selectAnotherUserInfo(@PathVariable Integer userId){
		log.info("[UserController] selectAnotherUserInfo Start - userId: {}", userId);

		UserDTO userDTO = userService.selectAnotherUserInfo(userId);

		log.info("[UserController] selectAnotherUserInfo End - userId: {}", userId);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", true, userDTO));
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


	@ApiOperation(value = "회원 탈퇴 요청", notes = "사용자 계정을 삭제합니다.", tags = { "UserController" })
	@DeleteMapping("/{userAccount}")
	public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String userAccount) {
		userService.deleteUserByAccount(userAccount);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "회원 탈퇴 성공", true, null));
	}
}
