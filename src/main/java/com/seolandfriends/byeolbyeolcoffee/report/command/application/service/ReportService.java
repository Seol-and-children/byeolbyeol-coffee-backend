package com.seolandfriends.byeolbyeolcoffee.report.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.report.command.application.dto.ReportDTO;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.aggregate.entity.Report;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.repository.RecipeIdRepository;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.repository.ReportRepository;

@Service
public class ReportService {
	private final ReportRepository reportRepository;
	private final RecipeIdRepository recipeIdRepository;
	private final ModelMapper modelMapper;
	@Autowired
	public ReportService(ReportRepository reportRepository, RecipeIdRepository recipeIdRepository, ModelMapper modelMapper){
		this.reportRepository = reportRepository;
		this.recipeIdRepository = recipeIdRepository;
		this.modelMapper = modelMapper;
	}

	// 새로운 신고 생성
	@Transactional
	public ReportDTO createRecipeReport(ReportDTO reportDTO) {
		Recipe recipe = recipeIdRepository.findById(reportDTO.getRecipeId()).orElseThrow(() -> new RuntimeException("신고 정보를 찾을 수 없습니다.. ID: " + reportDTO.getRecipeId()));
		String recipeTitle = recipe.getRecipeName();
		String recipeReported = recipe.getAuthor().getUserNickname();
		String recipeContent = recipe.getDescription();
		Report report = Report.builder()
			.reportCategory(reportDTO.getReportCategory())
			.authorName(reportDTO.getAuthorName())
			.reportReason(reportDTO.getReportReason())
			.processing(reportDTO.isProcessing())
			.recipe(recipe)
			.build();
		Report savedReport = reportRepository.save(report);
		ReportDTO reportDTO1 = modelMapper.map(savedReport, ReportDTO.class);
		reportDTO1.setReportedName(recipeReported);
		reportDTO1.setReportedContent(recipeContent);
		reportDTO1.setContentTitle(recipeTitle);
		return reportDTO1;
	}

	//신고 처리하기
	@Transactional
	public ReportDTO updateReport(Long reportId, ReportDTO reportDTO) {
		Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("신고 정보를 찾을 수 없습니다.. ID: " + reportId));
		if (report.isProcessing()) {
			report.processingCompleted();
		} else {
			report.processingBefore();
		}

		return modelMapper.map(report, ReportDTO.class);
	}


}
