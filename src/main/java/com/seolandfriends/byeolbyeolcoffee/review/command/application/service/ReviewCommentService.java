package com.seolandfriends.byeolbyeolcoffee.review.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewCommentDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewComment;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.repository.ReviewCommentRepository;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.repository.ReviewRepository;

@Service
public class ReviewCommentService {
	private final ReviewCommentRepository reviewCommentRepository;
	private final ReviewRepository reviewRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ReviewCommentService(ReviewCommentRepository reviewCommentRepository, ReviewRepository reviewRepository) {
		this.reviewCommentRepository = reviewCommentRepository;
		this.reviewRepository = reviewRepository;
	}

	public ReviewCommentDTO createReviewComment(ReviewCommentDTO reviewCommentDTO, Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new EntityNotFoundException("레시피를 찾을 수 없습니다."));

		ReviewComment parentComment = null;
		if (reviewCommentDTO.getParent() != null) {
			parentComment = reviewCommentRepository.findById(reviewCommentDTO.getParent().getCommentId())
				.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

			// 원 댓글에만 답글을 달 수 있도록 검증
			if (parentComment.getDepth() != 0) {
				throw new IllegalStateException("답글에는 답글을 달 수 없습니다.");
			}
		}

		// 답글의 경우 깊이를 1로 설정
		int depth = (parentComment != null) ? 1 : 0;

		ReviewComment newComment = ReviewComment.builder()
			.commentUser(reviewCommentDTO.getCommentUser())
			.content(reviewCommentDTO.getContent())
			.parent(parentComment)
			.depth(depth)
			.review(review)
			.build();

		ReviewComment savedComment = reviewCommentRepository.save(newComment);
		return modelMapper.map(savedComment, ReviewCommentDTO.class);
	}

	public ReviewCommentDTO getReviewCommentById(Long commentId) {
		ReviewComment comment = reviewCommentRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		return modelMapper.map(comment, ReviewCommentDTO.class);
	}

	public List<ReviewCommentDTO> getAllReviewComments() {
		List<ReviewComment> comments = reviewCommentRepository.findAll();
		return comments.stream()
			.map(comment -> modelMapper.map(comment, ReviewCommentDTO.class))
			.collect(Collectors.toList());
	}

	/* 댓글 수정 메소드 */
	public ReviewCommentDTO updateReviewComment(Long commentId, ReviewCommentDTO reviewCommentDTO) {
		ReviewComment existingComment = reviewCommentRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		existingComment.updateContent(reviewCommentDTO.getContent());

		ReviewComment updatedComment = reviewCommentRepository.save(existingComment);
		return modelMapper.map(updatedComment, ReviewCommentDTO.class);
	}

	/* 댓글 삭제 메소드 */
	public void deleteReviewComment(Long commentId) {
		ReviewComment comment = reviewCommentRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		// 댓글이 원 댓글인 경우
		if (comment.getDepth() == 0) {
			long repliesCount = reviewCommentRepository.countByParentCommentId(commentId);

			// 답글이 있는 원 댓글인 경우 내용만 변경
			if (repliesCount > 0) {
				comment.updateContent("(삭제된 댓글입니다)");
				reviewCommentRepository.save(comment);
			} else {
				// 답글이 없는 원 댓글은 삭제
				reviewCommentRepository.delete(comment);
			}
		} else {
			// 댓글이 답글인 경우
			ReviewComment parentComment = comment.getParent();
			reviewCommentRepository.delete(comment);

			// 원 댓글이 "(삭제된 댓글입니다)" 상태이고 다른 답글이 없는 경우 원 댓글도 삭제
			if (parentComment != null && "(삭제된 댓글입니다)".equals(parentComment.getContent())) {
				long repliesCount = reviewCommentRepository.countByParentCommentId(parentComment.getCommentId());
				if (repliesCount == 0) {
					reviewCommentRepository.delete(parentComment);
				}
			}
		}
	}
}