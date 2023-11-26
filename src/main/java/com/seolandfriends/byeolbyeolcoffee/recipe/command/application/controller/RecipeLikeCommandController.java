package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeLikeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service.RecipeLikeCommandService;

@RestController
@RequestMapping("/recipes/{recipeId}/likes")
public class RecipeLikeCommandController {

	private final RecipeLikeCommandService recipeLikeCommandService;

	public RecipeLikeCommandController(RecipeLikeCommandService recipeLikeCommandService) {
		this.recipeLikeCommandService = recipeLikeCommandService;
	}

	/* 좋아요 토글 메소드 */
	@PostMapping
	public ResponseEntity<RecipeLikeDto> toggleRecipeLike(@PathVariable("recipeId") Long recipeId,
		@RequestBody RecipeLikeDto recipeLikeDto) {
		RecipeLikeDto toggledLike = recipeLikeCommandService.toggleRecipeLike(recipeLikeDto, recipeId);
		return ResponseEntity.ok(toggledLike);
	}
}