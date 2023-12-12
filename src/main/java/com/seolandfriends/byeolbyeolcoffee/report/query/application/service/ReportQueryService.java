package com.seolandfriends.byeolbyeolcoffee.report.query.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.report.command.application.dto.ReportDTO;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.aggregate.entity.Report;
import com.seolandfriends.byeolbyeolcoffee.report.command.domain.repository.ReportRepository;

@Service
public class ReportQueryService {

	private final ReportRepository reportRepository;
	private final ModelMapper modelMapper;
	@Autowired
	public ReportQueryService(ReportRepository reportRepository, ModelMapper modelMapper){
		this.reportRepository = reportRepository;
		this.modelMapper = modelMapper;
	}

	//모든 신고 불러오기
	public List<ReportDTO> getAllReport(){
	// 	List<Report> reportList = reportRepository.findAll();
	// 	return reportList.stream().map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
	//
		List<Report> reportList = reportRepository.findAll();
		return reportList.stream()
			.map(this::mapReportWithRecipeInfo)
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


	//특정 신고 불러오기
	public ReportDTO getReport(Long reportId){
		Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("신고 정보를 찾을 수 없습니다.. ID: " + reportId));
		return modelMapper.map(report, ReportDTO.class);
	}
}
