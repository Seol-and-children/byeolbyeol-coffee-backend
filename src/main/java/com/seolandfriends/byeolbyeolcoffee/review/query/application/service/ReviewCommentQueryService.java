package com.seolandfriends.byeolbyeolcoffee.review.query.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewCommentDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewComment;
import com.seolandfriends.byeolbyeolcoffee.review.query.domain.repository.ReviewCommentQueryRepository;
@Service
public class ReviewCommentQueryService {
	private final ReviewCommentQueryRepository reviewCommentQueryRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ReviewCommentQueryService(ReviewCommentQueryRepository reviewCommentQueryRepository) {
		this.reviewCommentQueryRepository = reviewCommentQueryRepository;
	}

	/* 댓글Id로 댓글 정보 가져오기 */
	@Transactional
	public ReviewCommentDTO getReviewCommentById(Long commentId) {
		ReviewComment comment = reviewCommentQueryRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		return modelMapper.map(comment, ReviewCommentDTO.class);
	}

	/* 특정 리뷰의 모든 댓글 가져오기 */
	@Transactional
	public List<ReviewCommentDTO> getAllReviewComments(Long reviewId) {
		List<ReviewComment> comments = reviewCommentQueryRepository.findByReview_ReviewId(reviewId);
		return comments.stream()
			.map(comment -> modelMapper.map(comment, ReviewCommentDTO.class))
			.collect(Collectors.toList());
	}
}