package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeLikeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service.RecipeLikeCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
public class RecipeLikeCommandControllerTest {

	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@Mock
	private RecipeLikeCommandService recipeLikeCommandService;

	@InjectMocks
	private RecipeLikeCommandController recipeLikeCommandController;

	@BeforeEach
	public void setup() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(recipeLikeCommandController).build();
	}

	@Test
	public void whenToggleLike_thenStatus200() throws Exception {
		Long recipeId = 1L;
		RecipeLikeDto recipeLikeDto = new RecipeLikeDto();
		recipeLikeDto.setUserId("asdf");

		given(recipeLikeCommandService.toggleRecipeLike(recipeLikeDto, recipeId)).willReturn(recipeLikeDto);

		mockMvc.perform(post("/recipes/{recipeId}/likes", recipeId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(recipeLikeDto)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(recipeLikeDto)));
	}
}