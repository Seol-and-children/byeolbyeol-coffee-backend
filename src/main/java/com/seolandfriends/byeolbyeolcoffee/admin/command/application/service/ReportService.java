package com.seolandfriends.byeolbyeolcoffee.admin.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
		Report report = reportRepository.save(modelMapper.map(reportDTO, Report.class));
		return modelMapper.map(report, ReportDTO.class);
	}

	//모든 신고 불러오기
	public List<ReportDTO> getAllReport(){
		List<Report> reportList = reportRepository.findAll();
		return reportList.stream().map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
		//return reportRepository.findAll();
	}

	//
	@Transactional
	public ReportDTO updateReport(Long reportId, boolean isProcessed) {
		Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("신고 정보를 찾을 수 없습니다.. ID: " + reportId));
		if (isProcessed) {
			report.processingCompleted();
		} else {
			report.processingBefore();
		}

		return modelMapper.map(report, ReportDTO.class);
	}

	//
	public ReportDTO getReport(Long reportId){
		Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("신고 정보를 찾을 수 없습니다.. ID: " + reportId));
		return modelMapper.map(report, ReportDTO.class);
	}
}
