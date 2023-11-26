package com.seolandfriends.byeolbyeolcoffee.recipe.query.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeCommentDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.query.application.service.RecipeCommentQueryService;

@RestController
@RequestMapping("/recipes/{recipeId}/comments")
public class RecipeCommentQueryController {
	private final RecipeCommentQueryService recipeCommentQueryService;

	@Autowired
	public RecipeCommentQueryController(RecipeCommentQueryService recipeCommentQueryService) {
		this.recipeCommentQueryService = recipeCommentQueryService;
	}

	/* {commentId}를 가진 댓글 정보 불러오기 */
	@GetMapping("{commentId}")
	public ResponseEntity<RecipeCommentDto> getRecipeCommentById(
		@PathVariable("recipeId") Long recipeId,
		@PathVariable("commentId") Long commentId) {
		RecipeCommentDto recipeComment = recipeCommentQueryService.getRecipeCommentById(commentId);
		return ResponseEntity.ok(recipeComment);
	}

	/* 레시피에 달린 모든 댓글 정보 불러오기 */
	@GetMapping
	public ResponseEntity<List<RecipeCommentDto>> getAllRecipeComments(@PathVariable("recipeId") Long recipeId) {
		List<RecipeCommentDto> comments = recipeCommentQueryService.getAllRecipeComments(recipeId);
		return ResponseEntity.ok(comments);
	}
}
