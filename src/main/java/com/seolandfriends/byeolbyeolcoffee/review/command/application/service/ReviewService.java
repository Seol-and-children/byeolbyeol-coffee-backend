package com.seolandfriends.byeolbyeolcoffee.review.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository.ReviewRepository;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
 	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	/* 새로운 리뷰 생성 메소드 */
	public ReviewDTO createReview(ReviewDTO ReviewDTO) {
		Review newReview = Review.builder()
			.reviewName(ReviewDTO.getReviewName())
			.author(ReviewDTO.getAuthor())
			.registerTime(ReviewDTO.getRegisterTime())
			.content(ReviewDTO.getContent())
			.photoUrl(ReviewDTO.getPhotoUrl())
			.build();

		Review savedReview = reviewRepository.save(newReview);
		return modelMapper.map(savedReview, ReviewDTO.class);	}

	/* {Reviewid}를 가진 리뷰 정보 수정하기 */
	public ReviewDTO updateReview(Long reviewId, ReviewDTO reviewDTO) {
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다. ID: " + reviewId));

		review.updateReview(
			reviewDTO.getReviewName(),
			reviewDTO.getAuthor(),
			reviewDTO.getRegisterTime(),
			reviewDTO.getContent(),
			reviewDTO.getPhotoUrl()
			);

		Review savedReview = reviewRepository.save(review);
		return modelMapper.map(savedReview, ReviewDTO.class);	}

	/* 모든 레시피 정보 불러오기 */
	public List<ReviewDTO> getAllReviews() {
		List<Review> savedReviews = reviewRepository.findAll();
		return savedReviews.stream()
			.map(review -> modelMapper.map(review, ReviewDTO.class))
			.collect(Collectors.toList());
	}

	/* {ReviewId}를 가진 레시피 정보 불러오기 */
	public ReviewDTO getReviewById(Long reviewId) {
		Review savedReview = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다. ID: " + reviewId));
		savedReview.incrementViewsCount();
		savedReview = reviewRepository.save(savedReview);
		return modelMapper.map(savedReview, ReviewDTO.class);
	}

	/* {ReviewId}를 가진 리뷰 삭제하기 */
	public void deleteReview(Long ReviewId) {
		if (!reviewRepository.existsById(ReviewId)) {
			throw new EntityNotFoundException("리뷰를 찾을 수 없습니다. ID: " + ReviewId);
		}
		reviewRepository.deleteById(ReviewId);
	}

}