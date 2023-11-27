package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.common.ResponseDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.service.UserService;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "회원 조회 요청", notes = "회원 한명이 조회됩니다.", tags = { "MemberController" })
	@GetMapping("/{userId}")
	public ResponseEntity<ResponseDTO> selectMyUserInfo(@PathVariable String memberId){

		log.info("[MemberController]  selectMyMemberInfo   Start =============== ");
		log.info("[MemberController]  selectMyMemberInfo   {} ====== ", memberId);

		log.info("[MemberController]  selectMyMemberInfo   End ================= ");
		return ResponseEntity
			.ok()
			.body(new ResponseDTO(HttpStatus.OK, "조회 성공", userService.selectMyInfo(memberId)));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<ResponseDTO> updateUserInfo(@PathVariable String userAccount, @RequestBody UserDTO userDTO) {
		UserDTO updatedUserDTO = userService.updateUserInfo(userAccount, userDTO);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 수정 성공", updatedUserDTO));
	}

	@ApiOperation(value = "회원 탈퇴 요청", notes = "사용자 계정을 삭제합니다.", tags = { "UserController" })
	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String userAccount) {
		userService.deleteUserByAccount(userAccount);
		return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "회원 탈퇴 성공", null));
	}


}