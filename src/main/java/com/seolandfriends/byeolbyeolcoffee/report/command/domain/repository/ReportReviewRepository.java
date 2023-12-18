package com.seolandfriends.byeolbyeolcoffee.report.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.report.command.domain.aggregate.entity.ReportReview;
@Repository
public interface ReportReviewRepository extends JpaRepository<ReportReview, Long> {
}
