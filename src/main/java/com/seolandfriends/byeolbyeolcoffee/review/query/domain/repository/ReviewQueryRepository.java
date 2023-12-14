package com.seolandfriends.byeolbyeolcoffee.review.query.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;

public interface ReviewQueryRepository extends JpaRepository<Review, Long> {
	@Query("SELECT r FROM Review r JOIN FETCH r.author")
	List<Review> findAllReviewsWithUser();

	@Query("SELECT r.author.userNickname FROM Review r WHERE r.reviewId = :reviewId")
	String findUserNicknameByReviewId(@Param("reviewId") Long reviewId);
}