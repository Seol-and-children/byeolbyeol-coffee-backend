package com.seolandfriends.byeolbyeolcoffee.review.query.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.review.query.application.service.ReviewQueryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reviews")
@Slf4j
public class ReviewQueryController {

	private final ReviewQueryService reviewQueryService;

	@Autowired
	public ReviewQueryController(ReviewQueryService reviewQueryService) {
		this.reviewQueryService = reviewQueryService;
	}

	/* 모든 리뷰 정보 가져오기 */
	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getAllReviews() {
		List<ReviewDTO> reviews = reviewQueryService.getAllReviews();
		return ResponseEntity.ok(reviews);
	}

	/* Id로 리뷰 가져오기 */
	@GetMapping("/{reviewId}")
	public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long reviewId) {
		ReviewDTO review = reviewQueryService.getReviewById(reviewId);
		return ResponseEntity.ok(review);
	}
}