package com.seolandfriends.byeolbyeolcoffee.report.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;

@Repository
public interface ReviewIdRepository extends JpaRepository<Review, Long> {

	}
