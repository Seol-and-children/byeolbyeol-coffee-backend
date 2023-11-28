package com.seolandfriends.byeolbyeolcoffee.review.command.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.application.service.ReviewCommandService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reviews")
@Slf4j
public class ReviewCommandController {

	private final ReviewCommandService reviewCommandService;

	@Autowired
	public ReviewCommandController(ReviewCommandService reviewCommandService) {
		this.reviewCommandService = reviewCommandService;
	}

	/* 새로운 리뷰 생성 (create) */
	@PostMapping
	public ResponseEntity<ReviewDTO> createReview(@RequestPart MultipartFile reviewImage, @RequestPart ReviewDTO reviewDTO) {
		ReviewDTO newReview = reviewCommandService.createReview(reviewDTO, reviewImage);
		return ResponseEntity.ok(newReview);
	}

	/* 리뷰 수정 (update) */
	@PutMapping("/{reviewId}")
	public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDTO reviewDTO, @RequestParam(name = "reviewImage", required = false) MultipartFile reviewImage) {
		ReviewDTO updatedReview = reviewCommandService.updateReview(reviewId, reviewDTO, reviewImage);
		return ResponseEntity.ok(updatedReview);
	}

	/* Id로 리뷰 삭제하기 (delete) */
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
		reviewCommandService.deleteReview(reviewId);
		return ResponseEntity.ok( "삭제 완료");
	}

}