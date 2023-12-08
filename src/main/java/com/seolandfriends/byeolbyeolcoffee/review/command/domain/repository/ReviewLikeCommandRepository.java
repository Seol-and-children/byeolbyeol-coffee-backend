package com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewLike;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.LikeUserVO;

public interface ReviewLikeCommandRepository extends JpaRepository<ReviewLike, Long> {
	Optional<ReviewLike> findByReviewAndLikeUserVO(Review review, LikeUserVO likeUserVO);
}