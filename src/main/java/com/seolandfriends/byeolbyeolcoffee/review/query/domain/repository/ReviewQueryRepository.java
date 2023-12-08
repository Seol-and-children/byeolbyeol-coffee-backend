package com.seolandfriends.byeolbyeolcoffee.review.query.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;

public interface ReviewQueryRepository extends JpaRepository<Review, Long> {
}