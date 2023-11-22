package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeLikeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service.RecipeLikeService;

@RestController
@RequestMapping("/recipes/{recipeId}/likes")
public class RecipeLikeController {

	private final RecipeLikeService recipeLikeService;

	public RecipeLikeController(RecipeLikeService recipeLikeService) {
		this.recipeLikeService = recipeLikeService;
	}

	/* 좋아요 등록 메소드 */
	@PostMapping
	public ResponseEntity<RecipeLikeDto> createRecipeLike(@PathVariable("recipeId") Long recipeId,
		@RequestBody RecipeLikeDto recipeLikeDto) {
		RecipeLikeDto newLike = recipeLikeService.createRecipeLike(recipeLikeDto, recipeId);
		return ResponseEntity.ok(newLike);
	}

	/* 좋아요 삭제 메소드 */
	@DeleteMapping("/{likeId}")
	public ResponseEntity<?> deleteRecipeLike(@PathVariable Long likeId, @PathVariable Long recipeId) {
		recipeLikeService.deleteRecipeLike(likeId, recipeId);
		return ResponseEntity.ok( "좋아요 삭제 완료");
	}
}