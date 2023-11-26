package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeCommentDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service.RecipeCommentService;

@RestController
@RequestMapping("/recipes/{recipeId}/comments")
public class RecipeCommentController {
	private final RecipeCommentService recipeCommentService;

	@Autowired
	public RecipeCommentController(RecipeCommentService recipeCommentService) {
		this.recipeCommentService = recipeCommentService;
	}

	/* 새로운 댓글 등록 */
	@PostMapping
	public ResponseEntity<RecipeCommentDto> createRecipeComment(
		@PathVariable("recipeId") Long recipeId,
		@RequestBody RecipeCommentDto recipeCommentDto) {
		RecipeCommentDto newComment = recipeCommentService.createRecipeComment(recipeCommentDto, recipeId);
		return ResponseEntity.ok(newComment);
	}

	/* {commentId}를 가진 댓글 정보 불러오기 */
	@GetMapping("{commentId}")
	public ResponseEntity<RecipeCommentDto> getRecipeCommentById(
		@PathVariable("recipeId") Long recipeId,
		@PathVariable("commentId") Long commentId) {
		RecipeCommentDto recipeComment = recipeCommentService.getRecipeCommentById(commentId);
		return ResponseEntity.ok(recipeComment);
	}

	/* 레시피에 달린 모든 댓글 정보 불러오기 */
	@GetMapping
	public ResponseEntity<List<RecipeCommentDto>> getAllRecipeComments(@PathVariable("recipeId") Long recipeId) {
		List<RecipeCommentDto> comments = recipeCommentService.getAllRecipeComments(recipeId);
		return ResponseEntity.ok(comments);
	}

	/* 댓글 수정하기 */
	@PutMapping("/{commentId}")
	public ResponseEntity<RecipeCommentDto> updateRecipeComment(@PathVariable Long commentId,
		@RequestBody RecipeCommentDto recipeCommentDto, @PathVariable Long recipeId) {
		RecipeCommentDto updatedComment = recipeCommentService.updateRecipeComment(commentId, recipeCommentDto);
		return ResponseEntity.ok(updatedComment);
	}

	/* 댓글 삭제하기 */
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> deleteRecipeComment(@PathVariable Long commentId, @PathVariable Long recipeId) {
		recipeCommentService.deleteRecipeComment(commentId);
		return ResponseEntity.ok("댓글 삭제 완료");
	}
}
