package com.seolandfriends.byeolbyeolcoffee.review.command.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewCommentDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.application.service.ReviewCommentCommandService;

@RestController
@RequestMapping("/reviews/{reviewId}/comments")
public class ReviewCommentCommandController {
	private final ReviewCommentCommandService reviewCommentCommandService;

	@Autowired
	public ReviewCommentCommandController(ReviewCommentCommandService reviewCommentCommandService) {
		this.reviewCommentCommandService = reviewCommentCommandService;
	}

	/* 새로운 댓글 등록 (create) */
	@PostMapping
	public ResponseEntity<ReviewCommentDTO> createReviewComment(
		@PathVariable("reviewId") Long reviewId,
		@RequestBody ReviewCommentDTO reviewCommentDTO) {
		ReviewCommentDTO newComment = reviewCommentCommandService.createReviewComment(reviewCommentDTO, reviewId);
		return ResponseEntity.ok(newComment);
	}

	/* 댓글 수정하기 (update) */
	@PutMapping("/{commentId}")
	public ResponseEntity<ReviewCommentDTO> updateReviewComment(@PathVariable Long commentId,
		@RequestBody ReviewCommentDTO reviewCommentDTO, @PathVariable Long reviewId) {
		ReviewCommentDTO updatedComment = reviewCommentCommandService.updateReviewComment(commentId, reviewCommentDTO);
		return ResponseEntity.ok(updatedComment);
	}

	/* 댓글 삭제하기 (delete) */
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> deleteReviewComment(@PathVariable Long commentId, @PathVariable Long reviewId) {
		reviewCommentCommandService.deleteReviewComment(commentId);
		return ResponseEntity.ok("댓글 삭제 완료");
	}
}