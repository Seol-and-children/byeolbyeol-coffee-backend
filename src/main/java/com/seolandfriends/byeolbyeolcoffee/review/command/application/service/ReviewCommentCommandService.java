package com.seolandfriends.byeolbyeolcoffee.review.command.application.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewCommentDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewComment;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.CommentUserVO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository.ReviewCommandRepository;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository.ReviewCommentCommandRepository;

@Service
public class ReviewCommentCommandService {
	private final ReviewCommentCommandRepository reviewCommentCommandRepository;
	private final ReviewCommandRepository reviewCommandRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ReviewCommentCommandService(ReviewCommentCommandRepository reviewCommentCommandRepository,
		ReviewCommandRepository reviewCommandRepository) {
		this.reviewCommentCommandRepository = reviewCommentCommandRepository;
		this.reviewCommandRepository = reviewCommandRepository;
	}

	@Transactional
	public ReviewCommentDTO createReviewComment(ReviewCommentDTO reviewCommentDTO, Long reviewId) {
		Review review = reviewCommandRepository.findById(reviewId)
			.orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));

		ReviewComment parentComment = null;
		if (reviewCommentDTO.getParentId() != null) {
			parentComment = reviewCommentCommandRepository.findById(reviewCommentDTO.getParentId())
				.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

			// 원 댓글에만 답글을 달 수 있도록 검증
			if (parentComment.getDepth() != 0) {
				throw new IllegalStateException("답글에는 답글을 달 수 없습니다.");
			}
		}

		// 답글의 경우 깊이를 1로 설정
		int depth = (parentComment != null) ? 1 : 0;

		CommentUserVO commentUserVO = new CommentUserVO(reviewCommentDTO.getUserId());

		ReviewComment newComment = ReviewComment.builder()
			.commentUserVO(commentUserVO)
			.content(reviewCommentDTO.getContent())
			.parent(parentComment)
			.depth(depth)
			.review(review)
			.build();

		ReviewComment savedComment = reviewCommentCommandRepository.save(newComment);
		return modelMapper.map(savedComment, ReviewCommentDTO.class);
	}

	/* 댓글 수정 메소드 */
	@Transactional
	public ReviewCommentDTO updateReviewComment(Long commentId, ReviewCommentDTO reviewCommentDTO) {
		ReviewComment existingComment = reviewCommentCommandRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		existingComment.updateContent(reviewCommentDTO.getContent());

		ReviewComment updatedComment = reviewCommentCommandRepository.save(existingComment);
		return modelMapper.map(updatedComment, ReviewCommentDTO.class);
	}

	/* 댓글 삭제 메소드 */
	public void deleteReviewComment(Long commentId) {
		ReviewComment comment = reviewCommentCommandRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		// 댓글이 원 댓글인 경우
		if (comment.getDepth() == 0) {
			long repliesCount = reviewCommentCommandRepository.countByParentCommentId(commentId);

			// 답글이 있는 원 댓글인 경우 내용만 변경
			if (repliesCount > 0) {
				comment.updateContent("(삭제된 댓글입니다)");
				reviewCommentCommandRepository.save(comment);
			} else {
				// 답글이 없는 원 댓글은 삭제
				reviewCommentCommandRepository.delete(comment);
			}
		} else {
			// 댓글이 답글인 경우
			ReviewComment parentComment = comment.getParent();
			reviewCommentCommandRepository.delete(comment);

			// 원 댓글이 "(삭제된 댓글입니다)" 상태이고 다른 답글이 없는 경우 원 댓글도 삭제
			if (parentComment != null && "(삭제된 댓글입니다)".equals(parentComment.getContent())) {
				long repliesCount = reviewCommentCommandRepository.countByParentCommentId(parentComment.getCommentId());
				if (repliesCount == 0) {
					reviewCommentCommandRepository.delete(parentComment);
				}
			}
		}
	}
}