package com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.Report;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
