package com.seolandfriends.byeolbyeolcoffee.review.query.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewComment;

public interface ReviewCommentQueryRepository extends JpaRepository<ReviewComment, Long> {
	List<ReviewComment> findByReview_ReviewId(Long reviewId);
}