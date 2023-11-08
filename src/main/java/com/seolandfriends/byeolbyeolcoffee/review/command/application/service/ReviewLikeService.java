package com.seolandfriends.byeolbyeolcoffee.review.command.application.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewLikeDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewLike;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.repository.ReviewLikeRepository;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.repository.ReviewRepository;

@Service
public class ReviewLikeService {

	private final ReviewLikeRepository reviewLikeRepository;
	private final ReviewRepository reviewRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ReviewLikeService(ReviewLikeRepository reviewLikeRepository, ReviewRepository reviewRepository) {
		this.reviewLikeRepository = reviewLikeRepository;
		this.reviewRepository = reviewRepository;
	}

	/* 좋아요 등록 메소드 */
	public ReviewLikeDTO createReviewLike(ReviewLikeDTO reviewLikeDTO, Long revieweId) {
		Review review = reviewRepository.findById(revieweId)
			.orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));
		ReviewLike newReviewLike = ReviewLike.builder()
			.review(review)
			.likeUser(reviewLikeDTO.getLikeUser())
			.build();
		ReviewLike savedLike = reviewLikeRepository.save(newReviewLike);
		review.incrementLikesCount();
		reviewRepository.save(review);
		return modelMapper.map(savedLike, ReviewLikeDTO.class);
	}

	/* 좋아요 삭제 메소드 */
	public void deleteReviewLike(Long likeId, Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));
		if (!reviewLikeRepository.existsById(likeId)) {
			throw new RuntimeException("좋아요를 찾을 수 없습니다. ID: " + likeId);
		}
		review.decrementLikesCount();
		reviewRepository.save(review);
		reviewLikeRepository.deleteById(likeId);
	}
}