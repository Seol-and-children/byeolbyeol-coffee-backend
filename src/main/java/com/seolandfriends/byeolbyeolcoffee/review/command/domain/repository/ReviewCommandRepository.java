package com.seolandfriends.byeolbyeolcoffee.review.command.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;

public interface ReviewCommandRepository extends JpaRepository<Review, Long> {

}