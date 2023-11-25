package com.seolandfriends.byeolbyeolcoffee.admin.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.ReportDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.Report;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository.ReportRepository;

@Service
public class ReportService {
	private final ReportRepository reportRepository;
	private final ModelMapper modelMapper;
	@Autowired
	public ReportService(ReportRepository reportRepository, ModelMapper modelMapper){
		this.reportRepository = reportRepository;
		this.modelMapper = modelMapper;
	}

	// 새로운 신고 생성
	@Transactional
	public ReportDTO createReport(ReportDTO reportDTO) {
		//Report report = reportRepository.save(modelMapper.map(reportDTO, Report.class));
		Report report = Report.builder()
			.reportedName(reportDTO.getReportedName())
			.authorName(reportDTO.getAuthorName())
			.reportReason(reportDTO.getReportReason())
			.reportedContent(reportDTO.getReportedContent())
			.reportTime(reportDTO.getReportTime())
			.contentTitle(reportDTO.getContentTitle())
			.processing(reportDTO.isProcessing())
			.build();
		Report savedReport = reportRepository.save(report);
		return modelMapper.map(savedReport, ReportDTO.class);
	}

	//모든 신고 불러오기
	public List<ReportDTO> getAllReport(){
		List<Report> reportList = reportRepository.findAll();
		return reportList.stream().map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
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

	//특정 신고 불러오기
	public ReportDTO getReport(Long reportId){
		Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("신고 정보를 찾을 수 없습니다.. ID: " + reportId));
		return modelMapper.map(report, ReportDTO.class);
	}
}
