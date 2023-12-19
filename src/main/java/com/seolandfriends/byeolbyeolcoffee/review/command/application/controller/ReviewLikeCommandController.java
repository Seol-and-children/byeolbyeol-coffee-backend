package com.seolandfriends.byeolbyeolcoffee.review.command.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewLikeDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.application.service.ReviewLikeCommandService;

@RestController
@RequestMapping("/reviews/{reviewId}/likes")
public class ReviewLikeCommandController {

	private final ReviewLikeCommandService reviewLikeCommandService;

	public ReviewLikeCommandController(ReviewLikeCommandService reviewLikeCommandService) {
		this.reviewLikeCommandService = reviewLikeCommandService;
	}

	/* 좋아요 토글 메소드 */
	@PostMapping
	public ResponseEntity<ReviewLikeDTO> toggleReviewLike(@PathVariable("reviewId") Long reviewId,
		@RequestBody ReviewLikeDTO reviewLikeDTO) {
		ReviewLikeDTO toggledLike = reviewLikeCommandService.toggleReviewLike(reviewLikeDTO, reviewId);
		return ResponseEntity.ok(toggledLike);
	}

	/* 사용자의 좋아요 상태 확인 메소드 */
	@GetMapping("/status")
	public ResponseEntity<Boolean> checkLikeStatus(
		@PathVariable("reviewId") Long reviewId,
		@RequestParam("userId") int userId) {

		boolean isLiked = reviewLikeCommandService.checkIfUserLikedReview(reviewId, userId);
		return ResponseEntity.ok(isLiked);
	}
}