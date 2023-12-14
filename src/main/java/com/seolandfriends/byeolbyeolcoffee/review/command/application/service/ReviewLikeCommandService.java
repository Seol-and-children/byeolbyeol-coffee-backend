package com.seolandfriends.byeolbyeolcoffee.review.command.application.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewLikeDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewLike;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.LikeUserVO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository.ReviewCommandRepository;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository.ReviewLikeCommandRepository;

@Service
public class ReviewLikeCommandService {

	private final ReviewLikeCommandRepository reviewLikeCommandRepository;
	private final ReviewCommandRepository reviewCommandRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ReviewLikeCommandService(ReviewLikeCommandRepository reviewLikeCommandRepository, ReviewCommandRepository reviewCommandRepository) {
		this.reviewLikeCommandRepository = reviewLikeCommandRepository;
		this.reviewCommandRepository = reviewCommandRepository;
	}

	/* 좋아요 토글 메소드 */
	public ReviewLikeDTO toggleReviewLike(ReviewLikeDTO reviewLikeDTO, Long reviewId) {
		Review review = reviewCommandRepository.findById(reviewId)
			.orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));
		LikeUserVO likeUserVO = new LikeUserVO(reviewLikeDTO.getUserId());

		// 사용자가 이미 해당 레시피에 좋아요를 눌렀는지 확인
		Optional<ReviewLike> existingLike = reviewLikeCommandRepository.findByReviewAndLikeUserVO(review, likeUserVO);

		if (existingLike.isPresent()) {
			// 좋아요가 이미 등록되어 있다면 좋아요를 취소하고 카운트 감소
			review.decrementLikesCount();
			reviewLikeCommandRepository.delete(existingLike.get());
		} else {
			// 좋아요가 등록되어 있지 않다면 좋아요를 등록하고 카운트 증가
			ReviewLike newReviewLike = ReviewLike.builder()
				.review(review)
				.likeUserVO(likeUserVO)
				.build();
			review.incrementLikesCount();
			reviewLikeCommandRepository.save(newReviewLike);
		}
		reviewCommandRepository.save(review);
		return modelMapper.map(reviewLikeDTO, ReviewLikeDTO.class);
	}
}