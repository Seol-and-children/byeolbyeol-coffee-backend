package com.seolandfriends.byeolbyeolcoffee.review.command.application.controller;

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

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewCommentDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.application.service.ReviewCommentService;

@RestController
@RequestMapping("/reviews/{reviewId}/comments")
public class ReviewCommentController {
	private final ReviewCommentService reviewCommentService;

	@Autowired
	public ReviewCommentController(ReviewCommentService reviewCommentService) {
		this.reviewCommentService = reviewCommentService;
	}

	/* 새로운 댓글 등록 */
	@PostMapping
	public ResponseEntity<ReviewCommentDTO> createReviewComment(
		@PathVariable("reviewId") Long reviewId,
		@RequestBody ReviewCommentDTO reviewCommentDTO) {
		ReviewCommentDTO newComment = reviewCommentService.createReviewComment(reviewCommentDTO, reviewId);
		return ResponseEntity.ok(newComment);
	}

	/* {commentId}를 가진 댓글 정보 불러오기 */
	@GetMapping("{commentId}")
	public ResponseEntity<ReviewCommentDTO> getReviewCommentById(
		@PathVariable("reviewId") Long reviewId,
		@PathVariable("commentId") Long commentId) {
		ReviewCommentDTO reviewComment = reviewCommentService.getReviewCommentById(commentId);
		return ResponseEntity.ok(reviewComment);
	}

	/* 레시피에 달린 모든 댓글 정보 불러오기 */
	@GetMapping
	public ResponseEntity<List<ReviewCommentDTO>> getAllReviewComments(@PathVariable Long reviewId) {
		List<ReviewCommentDTO> comments = reviewCommentService.getAllReviewComments();
		return ResponseEntity.ok(comments);
	}

	/* 댓글 수정하기 */
	@PutMapping("/{commentId}")
	public ResponseEntity<ReviewCommentDTO> updateReviewComment(@PathVariable Long commentId,
		@RequestBody ReviewCommentDTO reviewCommentDTO, @PathVariable Long reviewId) {
		ReviewCommentDTO updatedComment = reviewCommentService.updateReviewComment(commentId, reviewCommentDTO);
		return ResponseEntity.ok(updatedComment);
	}

	/* 댓글 삭제하기 */
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> deleteReviewComment(@PathVariable Long commentId, @PathVariable Long reviewId) {
		reviewCommentService.deleteReviewComment(commentId);
		return ResponseEntity.ok("댓글 삭제 완료");
	}
}