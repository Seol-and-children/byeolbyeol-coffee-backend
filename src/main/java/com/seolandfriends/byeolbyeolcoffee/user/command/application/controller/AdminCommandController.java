package com.seolandfriends.byeolbyeolcoffee.user.command.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.common.annotation.AdminAuthorize;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 관리자용 사용자 커맨드 API를 처리하는 컨트롤러 클래스입니다.
 * AdminAuthorize 어노테이션을 통해 인증된 관리자만 접근할 수 있도록 설정되어 있습니다.
 */
@Tag(name = "관리자용 COMMAND API")
@RequiredArgsConstructor
@AdminAuthorize
@RestController
@RequestMapping("/users/admin")
public class AdminCommandController {

}
