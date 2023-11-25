package com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.Report;
@Repository
public interface SearchRepository extends JpaRepository<Report, Long> {
	//reportedContent라는 필드에서 받아온 query문을 포함한 것들을 검색해서 가져옴
	List<Report> findByreportedContentContaining(String query);
}
