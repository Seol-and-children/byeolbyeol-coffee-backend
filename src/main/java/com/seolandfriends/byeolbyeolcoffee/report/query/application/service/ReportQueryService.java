package com.seolandfriends.byeolbyeolcoffee.report.query.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.report.command.application.dto.ReportDTO;
import com.seolandfriends.byeolbyeolcoffee.report.command.application.dto.ReportReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.aggregate.entity.Report;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.aggregate.entity.ReportReview;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.repository.ReportRepository;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.repository.ReportReviewRepository;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;

@Service
public class ReportQueryService {

	private final ReportRepository reportRepository;
	private final ReportReviewRepository reportReviewRepository;
	private final ModelMapper modelMapper;
	@Autowired
	public ReportQueryService(ReportRepository reportRepository, ReportReviewRepository reportReviewRepository, ModelMapper modelMapper){
		this.reportRepository = reportRepository;
		this.reportReviewRepository = reportReviewRepository;
		this.modelMapper = modelMapper;
	}

	//모든 레시피 신고 불러오기
	public List<ReportDTO> getAllReport(){
		List<Report> reportList = reportRepository.findAll();
		return reportList.stream()
			.map(this::mapReportWithRecipeInfo)
			.collect(Collectors.toList());
	}

	//모든 리뷰 신고 불러오기
	public List<ReportReviewDTO> getAllReviewReport(){
		List<ReportReview> reportList = reportReviewRepository.findAll();
		return reportList.stream()
			.map(this::mapReportWithReviewInfo)
			.collect(Collectors.toList());
	}

	private ReportDTO mapReportWithRecipeInfo(Report report) {
		ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);

		// 레포트에 연관된 레시피 정보 가져오기
		Recipe recipe = report.getRecipe(); // 이 부분은 실제 데이터 모델에 따라 다를 수 있습니다.
		if (recipe != null) {
			reportDTO.setReportedContent(recipe.getDescription());
			reportDTO.setContentTitle(recipe.getRecipeName());
			reportDTO.setReportedName(recipe.getAuthor().getUserNickname());
		}
		return reportDTO;
		}

	private ReportReviewDTO mapReportWithReviewInfo(ReportReview report) {
		ReportReviewDTO reportReviewDTO = modelMapper.map(report, ReportReviewDTO.class);

		// 레포트에 연관된 레시피 정보 가져오기
		Review review = report.getReview(); // 이 부분은 실제 데이터 모델에 따라 다를 수 있습니다.
		if (review != null) {
			reportReviewDTO.setReportedContent(review.getContent());
			reportReviewDTO.setContentTitle(review.getReviewName());
			reportReviewDTO.setReportedName(review.getAuthor().getUserNickname());
		}
		return reportReviewDTO;
	}


	//특정 신고 불러오기
	public ReportDTO getReport(Long reportId){
		Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("신고 정보를 찾을 수 없습니다.. ID: " + reportId));
		return modelMapper.map(report, ReportDTO.class);
	}

	public ReportReviewDTO getReviewReport(Long reportId){
		ReportReview report = reportReviewRepository.findById(reportId).orElseThrow(() -> new RuntimeException("신고 정보를 찾을 수 없습니다.. ID: " + reportId));
		return modelMapper.map(report, ReportReviewDTO.class);
	}
}
