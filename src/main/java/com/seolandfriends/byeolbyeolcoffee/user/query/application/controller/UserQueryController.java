package com.seolandfriends.byeolbyeolcoffee.user.query.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.common.ResponseDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.service.UserService;
import com.seolandfriends.byeolbyeolcoffee.user.query.application.service.UserQueryService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserQueryController {
	private final UserQueryService userQueryService;

	public UserQueryController(UserQueryService userQueryService)	 {
		this.userQueryService = userQueryService;
	}

	@ApiOperation(value = "회원 조회 요청", notes = "회원 한명이 조회됩니다.", tags = { "UserController" })
	@GetMapping("/{userAccount}")
	public ResponseEntity<ResponseDTO> selectMyUserInfo(@PathVariable String userAccount){
		log.info("[UserController] selectMyUserInfo Start - userAccount: {}", userAccount);

		UserDTO userDTO = userQueryService.selectMyInfo(userAccount);

		log.info("[UserController] selectMyUserInfo End - userAccount: {}", userAccount);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", true, userDTO));
	}

	@GetMapping("/other/{userId}")
	public ResponseEntity<ResponseDTO> selectAnotherUserInfo(@PathVariable Integer userId){
		log.info("[UserController] selectAnotherUserInfo Start - userId: {}", userId);

		UserDTO userDTO = userQueryService.selectAnotherUserInfo(userId);

		log.info("[UserController] selectAnotherUserInfo End - userId: {}", userId);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", true, userDTO));
	}

}
