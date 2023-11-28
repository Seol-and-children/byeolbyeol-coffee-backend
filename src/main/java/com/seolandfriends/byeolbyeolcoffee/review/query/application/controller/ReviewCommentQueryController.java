package com.seolandfriends.byeolbyeolcoffee.review.query.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewCommentDTO;
import com.seolandfriends.byeolbyeolcoffee.review.query.application.service.ReviewCommentQueryService;

@RestController
@RequestMapping("/reviews/{reviewId}/comments")
public class ReviewCommentQueryController {
	private final ReviewCommentQueryService reviewCommentQueryService;

	@Autowired
	public ReviewCommentQueryController(ReviewCommentQueryService reviewCommentQueryService) {
		this.reviewCommentQueryService = reviewCommentQueryService;
	}

	/* {commentId}를 가진 댓글 정보 불러오기 */
	@GetMapping("{commentId}")
	public ResponseEntity<ReviewCommentDTO> getReviewCommentById(
		@PathVariable("reviewId") Long reviewId,
		@PathVariable("commentId") Long commentId) {
		ReviewCommentDTO reviewComment = reviewCommentQueryService.getReviewCommentById(commentId);
		return ResponseEntity.ok(reviewComment);
	}

	/* 리뷰에 달린 모든 댓글 정보 불러오기 */
	@GetMapping
	public ResponseEntity<List<ReviewCommentDTO>> getAllReviewComments(@PathVariable("reviewId") Long reviewId) {
		List<ReviewCommentDTO> comments = reviewCommentQueryService.getAllReviewComments(reviewId);
		return ResponseEntity.ok(comments);
	}
}