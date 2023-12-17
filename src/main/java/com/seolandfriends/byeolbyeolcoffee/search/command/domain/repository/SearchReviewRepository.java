package com.seolandfriends.byeolbyeolcoffee.search.command.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
@Repository
public interface SearchReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByReviewNameContaining(String query);
	List<Review> findByAuthorUserNicknameContaining(String query);
}
