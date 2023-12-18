package com.seolandfriends.byeolbyeolcoffee.review.query.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.query.domain.repository.ReviewQueryRepository;

@Service
public class ReviewQueryService {
	private final ReviewQueryRepository reviewQueryRepository;
	ModelMapper modelMapper = new ModelMapper();

	/* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	@Value("${image.image-url}")
	private String IMAGE_URL;

	@Autowired
	public ReviewQueryService(ReviewQueryRepository reviewQueryRepository) {
		this.reviewQueryRepository = reviewQueryRepository;
	}

	public String getUserNicknameByReviewId(Long reviewId) {
		return reviewQueryRepository.findUserNicknameByReviewId(reviewId);
	}

	/* 모든 리뷰 정보 불러오기 */
	public List<ReviewDTO> getAllReviews() {
		List<Review> savedReviews = reviewQueryRepository.findAllReviewsWithUser();
		return savedReviews.stream()
			.map(review -> {
				ReviewDTO reviewDto = modelMapper.map(review, ReviewDTO.class);
				reviewDto.setUserNickname(review.getAuthor().getUserNickname());
				return reviewDto;
			})
			.collect(Collectors.toList());
	}

	/* {reviewId}를 가진 리뷰 정보 불러오기 */
	public ReviewDTO getReviewById(Long reviewId) {
		Review savedReview = reviewQueryRepository.findById(reviewId)
			.orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다. ID: " + reviewId));
		savedReview.incrementViewsCount();
		savedReview = reviewQueryRepository.save(savedReview);

		ReviewDTO reviewDTO = modelMapper.map(savedReview, ReviewDTO.class);
		reviewDTO.setUserNickname(getUserNicknameByReviewId(savedReview.getReviewId()));

		return reviewDTO;
	}
}