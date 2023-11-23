package com.seolandfriends.byeolbyeolcoffee.review.command.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewLikeDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.application.service.ReviewLikeService;

@RestController
@RequestMapping("/reviews/{reviewId}/likes")
public class ReviewLikeController {

	private final ReviewLikeService reviewLikeService;

	public ReviewLikeController(ReviewLikeService reviewLikeService) {
		this.reviewLikeService = reviewLikeService;
	}

	/* 좋아요 등록 메소드 */
	@PostMapping
	public ResponseEntity<ReviewLikeDTO> createReviewLike(@PathVariable("reviewId") Long reviewId,
		@RequestBody ReviewLikeDTO reviewLikeDTO) {
		ReviewLikeDTO newLike = reviewLikeService.createReviewLike(reviewLikeDTO, reviewId);
		return ResponseEntity.ok(newLike);
	}

	/* 좋아요 삭제 메소드 */
	@DeleteMapping("/{likeId}")
	public ResponseEntity<?> deleteReviewLike(@PathVariable Long likeId, @PathVariable Long reviewId) {
		reviewLikeService.deleteReviewLike(likeId, reviewId);
		return ResponseEntity.ok( "좋아요 삭제 완료");
	}
}