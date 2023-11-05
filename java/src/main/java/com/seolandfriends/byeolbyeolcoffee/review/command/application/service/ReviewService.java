package com.seolandfriends.byeolbyeolcoffee.review.command.application.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.repository.ReviewRepository;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;

@Service
public class ReviewService {

	private final ReviewRepository ReviewRepository;

	@Autowired
	public ReviewService(ReviewRepository ReviewRepository) {
		this.ReviewRepository = ReviewRepository;
	}

	/* 새로운 레시피 생성 메소드 */
	public Review createReview(ReviewDTO ReviewDTO) {
		Review newReview = Review.builder()
			.ReviewName(ReviewDTO.getReviewName())
			.ReviewPhoto(ReviewDTO.getReviewPhoto())
			.description(ReviewDTO.getDescription())
			.franchiseCafe(ReviewDTO.getFranchiseCafe())
			.baseBeverage(ReviewDTO.getBaseBeverage())
			.customOptions(ReviewDTO.getCustomOptions())
			.author(ReviewDTO.getAuthor())
			.build();

		return ReviewRepository.save(newReview);
	}

	/* {Reviewid}를 가진 레시피 정보 수정하기 */
	public Review updateReview(Long ReviewId, ReviewDTO ReviewDTO) {
		Review Review = ReviewRepository.findById(ReviewId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + ReviewId));

		Review.updateReview(
			ReviewDTO.getReviewName(),
			ReviewDTO.getReviewPhoto(),
			ReviewDTO.getDescription(),
			ReviewDTO.getFranchiseCafe(),
			ReviewDTO.getBaseBeverage(),
			ReviewDTO.getCustomOptions()
		);

		return ReviewRepository.save(Review);
	}

	/* 모든 레시피 정보 불러오기 */
	public List<Review> getAllReviews() {
		return ReviewRepository.findAll();
	}

	/* {ReviewId}를 가진 레시피 정보 불러오기 */
	public Review getReviewById(Long ReviewId) {
		return ReviewRepository.findById(ReviewId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + ReviewId));
	}

	/* {ReviewId}를 가진 레시피 삭제하기 */
	public void deleteReview(Long ReviewId) {
		if (!ReviewRepository.existsById(ReviewId)) {
			throw new EntityNotFoundException("레시피를 찾을 수 없습니다. ID: " + ReviewId);
		}
		ReviewRepository.deleteById(ReviewId);
	}

}