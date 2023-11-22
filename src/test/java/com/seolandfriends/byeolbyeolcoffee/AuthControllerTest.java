package com.seolandfriends.byeolbyeolcoffee;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.seolandfriends.byeolbyeolcoffee.user.command.application.controller.AuthController;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.service.AuthService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.TokenDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AuthControllerTest {

	@Mock
	private AuthService authService;

	@InjectMocks
	private AuthController authController;

	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	void whenSignupIsValid_thenReturnsSuccess() throws Exception {
		// Given
		UserDTO newUserDTO = new UserDTO();
		newUserDTO.setUserEmail("newuser@example.com");
		newUserDTO.setUserPassword("newpassword");

		given(authService.signup(any(UserDTO.class))).willReturn(newUserDTO);

		// When & Then
		mockMvc.perform(post("/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newUserDTO)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value(HttpStatus.CREATED.value()))
			.andExpect(jsonPath("$.message").value("회원가입 성공"))
			.andExpect(jsonPath("$.data.userId").value(newUserDTO.getUserId().toString())); // 이 부분은 실제 반환 값에 따라 달라질 수 있습니다.
	}

	@Test
	void whenLoginIsValid_thenReturnsToken() throws Exception {
		// Given
		UserDTO validUserDTO = new UserDTO();
		validUserDTO.setUserEmail("user@example.com");
		validUserDTO.setUserPassword("password");

		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setAccessToken("mockedAccessToken");

		given(authService.login(any(UserDTO.class))).willReturn(tokenDTO);

		// When & Then
		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(validUserDTO)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.message").value("로그인 성공"))
			.andExpect(jsonPath("$.data").value("mockedAccessToken"));
	}

}
