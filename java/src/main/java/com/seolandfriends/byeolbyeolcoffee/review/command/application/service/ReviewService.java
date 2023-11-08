package com.seolandfriends.byeolbyeolcoffee.review.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.repository.ReviewRepository;

@Service
public class ReviewService {

	private final ReviewRepository ReviewRepository;
 	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ReviewService(ReviewRepository ReviewRepository) {

		this.ReviewRepository = ReviewRepository;
	}

	/* 새로운 리뷰 생성 메소드 */
	public ReviewDTO createReview(ReviewDTO ReviewDTO) {
		Review newReview = Review.builder()
			.reviewName(ReviewDTO.getReviewName())
			.content(ReviewDTO.getContent())
			.photoUrl(ReviewDTO.getPhotoUrl())
			.author(ReviewDTO.getAuthor())
			.registerTime(ReviewDTO.getRegisterTime())
			.recommendations(ReviewDTO.getRecommendations())
			.views(ReviewDTO.getViews())
			.build();

		Review savedReview = ReviewRepository.save(newReview);
		return modelMapper.map(savedReview, ReviewDTO.class);	}

	/* {Reviewid}를 가진 리뷰 정보 수정하기 */
	public ReviewDTO updateReview(Long ReviewId, ReviewDTO ReviewDTO) {
		Review  Review = ReviewRepository.findById(ReviewId)
			.orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다. ID: " + ReviewId));

		Review.updateReview(
			ReviewDTO.getReviewName(),
			ReviewDTO.getContent(),
			ReviewDTO.getPhotoUrl(),
			ReviewDTO.getAuthor(),
			ReviewDTO.getRegisterTime(),
			ReviewDTO.getRecommendations(),
			ReviewDTO.getViews()
			);

		Review savedReview = ReviewRepository.save(review);
		return ReviewRepository.save(Review);
	}

	/* 모든 레시피 정보 불러오기 */
	public List<ReviewDTO> getAllReviews() {
		List<Review> savedReviews = ReviewRepository.findAll();
		return savedReviews.stream()
			.map(review -> modelMapper.map(review, ReviewDTO.class))
			.collect(Collectors.toList());
	}

	/* {ReviewId}를 가진 레시피 정보 불러오기 */
	public ReviewDTO getReviewById(Long reviewId) {
		Review savedReview = ReviewRepository.findById(reviewId)
			.orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다. ID: " + reviewId));
		savedReview.incrementViewsCount();
		savedReview = ReviewRepository.save(savedReview);
		return modelMapper.map(savedReview, ReviewDTO.class);
	}

	/* {ReviewId}를 가진 리뷰 삭제하기 */
	public void deleteReview(Long ReviewId) {
		if (!ReviewRepository.existsById(ReviewId)) {
			throw new EntityNotFoundException("리뷰를 찾을 수 없습니다. ID: " + ReviewId);
		}
		ReviewRepository.deleteById(ReviewId);
	}

}