package com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewLike;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
}