package com.seolandfriends.byeolbyeolcoffee.review.command.application.service;

import java.io.IOException;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO.ReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.ReviewUserVO;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository.ReviewCommandRepository;
import com.seolandfriends.byeolbyeolcoffee.util.FileUploadUtils;

@Service
public class ReviewCommandService {
	private final ReviewCommandRepository reviewCommandRepository;
	ModelMapper modelMapper = new ModelMapper();

	/* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	@Value("${image.image-url}")
	private String IMAGE_URL;

	@Autowired
	public ReviewCommandService(ReviewCommandRepository reviewCommandRepository) {
		this.reviewCommandRepository = reviewCommandRepository;
	}

	/* 새로운 리뷰 생성 메소드 */
	@Transactional
	public ReviewDTO createReview(ReviewDTO reviewDTO, MultipartFile reviewImage) {
		String imageName = UUID.randomUUID().toString().replace("-", "");
		String replaceFileName = null;

		try {
			replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, reviewImage);

			reviewDTO.setPhotoUrl(replaceFileName);
			ReviewUserVO author = new ReviewUserVO(reviewDTO.getAuthorId());

			Review newReview = Review.builder()
				.reviewName(reviewDTO.getReviewName())
				.photoUrl(reviewDTO.getPhotoUrl())
				.content(reviewDTO.getContent())
				.author(author)
				.likesCount(0)
				.viewsCount(0)
				.build();

			Review savedReview = reviewCommandRepository.save(newReview);
			return modelMapper.map(savedReview, ReviewDTO.class);
		} catch (Exception e) {
			FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			throw new RuntimeException(e);
		}
	}

	/* {reviewid}를 가진 리뷰 정보 수정하기 */
	@Transactional
	public ReviewDTO updateReview(Long reviewId, ReviewDTO reviewDTO, MultipartFile reviewImage) {
		String replaceFileName = null;

		try {
			Review review = reviewCommandRepository.findById(reviewId)
				.orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다. ID: " + reviewId));
			String oriImage = review.getPhotoUrl();

			Review updateReview = Review.builder()
				.reviewName(reviewDTO.getReviewName())
				.photoUrl(reviewDTO.getPhotoUrl())
				.content(reviewDTO.getContent())
				.build();

			if (reviewImage != null) {
				String imageName = UUID.randomUUID().toString().replace("-", "");
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, reviewImage);

				updateReview = updateReview.toBuilder().photoUrl(replaceFileName).build();


			} else {
				/* 이미지 변경 없을 시 */
				updateReview = updateReview.toBuilder().photoUrl(oriImage).build();
			}

			Review savedReview = reviewCommandRepository.save(updateReview);
			return modelMapper.map(savedReview, ReviewDTO.class);
		} catch (IOException e) {
			FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			throw new RuntimeException(e);
		}
	}

	/* {reviewId}를 가진 리뷰 삭제하기 */
	public void deleteReview(Long reviewId) {
		if (!reviewCommandRepository.existsById(reviewId)) {
			throw new RuntimeException("리뷰를 찾을 수 없습니다. ID: " + reviewId);
		}
		reviewCommandRepository.deleteById(reviewId);
	}

}