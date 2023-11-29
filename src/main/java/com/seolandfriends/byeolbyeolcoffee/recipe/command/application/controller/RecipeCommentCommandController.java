package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeCommentDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service.RecipeCommentCommandService;

@RestController
@RequestMapping("/recipes/{recipeId}/comments")
public class RecipeCommentCommandController {
	private final RecipeCommentCommandService recipeCommentCommandService;

	@Autowired
	public RecipeCommentCommandController(RecipeCommentCommandService recipeCommentCommandService) {
		this.recipeCommentCommandService = recipeCommentCommandService;
	}

	/* 새로운 댓글 등록 (create) */
	@PostMapping
	public ResponseEntity<RecipeCommentDto> createRecipeComment(
		@PathVariable("recipeId") Long recipeId,
		@RequestBody RecipeCommentDto recipeCommentDto) {
		RecipeCommentDto newComment = recipeCommentCommandService.createRecipeComment(recipeCommentDto, recipeId);
		return ResponseEntity.ok(newComment);
	}

	/* 댓글 수정하기 (update) */
	@PutMapping("/{commentId}")
	public ResponseEntity<RecipeCommentDto> updateRecipeComment(@PathVariable Long commentId,
		@RequestBody RecipeCommentDto recipeCommentDto, @PathVariable Long recipeId) {
		RecipeCommentDto updatedComment = recipeCommentCommandService.updateRecipeComment(commentId, recipeCommentDto, recipeId);
		return ResponseEntity.ok(updatedComment);
	}

	/* 댓글 삭제하기 (delete) */
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteRecipeComment(
		@PathVariable Long commentId,
		@PathVariable Long recipeId) {
		recipeCommentCommandService.deleteRecipeComment(recipeId, commentId);
		return ResponseEntity.ok().build();
	}
}
