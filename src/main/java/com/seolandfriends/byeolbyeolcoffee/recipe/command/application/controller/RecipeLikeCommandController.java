package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		@RequestBody @Valid RecipeLikeDto recipeLikeDto) {
		RecipeLikeDto toggledLike = recipeLikeCommandService.toggleRecipeLike(recipeLikeDto, recipeId);
		return ResponseEntity.ok(toggledLike);
	}

	/* 사용자의 좋아요 상태 확인 메소드 */
	@GetMapping("/status")
	public ResponseEntity<Boolean> checkLikeStatus(
		@PathVariable("recipeId") Long recipeId,
		@RequestParam("userId") int userId) {

		boolean isLiked = recipeLikeCommandService.checkIfUserLikedRecipe(recipeId, userId);
		return ResponseEntity.ok(isLiked);
	}
}