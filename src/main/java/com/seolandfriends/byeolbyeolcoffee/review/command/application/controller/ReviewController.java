package com.seolandfriends.byeolbyeolcoffee.review.command.application.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.application.service.ReviewService;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;

@RestController
@Transactional
@RequestMapping("/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	/* 새로운 리뷰 생성 */
	@PostMapping
	public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
		ReviewDTO newReview = reviewService.createReview(reviewDTO);
		return ResponseEntity.ok(newReview);
	}

	/* 리뷰 수정 */
	@PutMapping("/{reviewId}")
	public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDTO reviewDTO) {
		ReviewDTO updatedReview = reviewService.updateReview(reviewId, reviewDTO);
		return ResponseEntity.ok(updatedReview);
	}

	/* 모든 리뷰 정보 가져오기 */
	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getAllReviews() {
		List<ReviewDTO> reviews = reviewService.getAllReviews();
		return ResponseEntity.ok(reviews);
	}

	/* Id로 리뷰 가져오기 */
	@GetMapping("/{reviewId}")
	public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long reviewId) {
		ReviewDTO review = reviewService.getReviewById(reviewId);
		return ResponseEntity.ok(review);
	}

	/* Id로 리뷰 삭제하기 */
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
		reviewService.deleteReview(reviewId);
		return ResponseEntity.ok( "삭제 완료");
	}

}