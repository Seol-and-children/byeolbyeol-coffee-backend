package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
public interface ReviewRepository extends JpaRepository<Review, Long> {
}