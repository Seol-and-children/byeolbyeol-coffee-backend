package com.seolandfriends.byeolbyeolcoffee.report.command.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.report.command.application.dto.ReportDTO;
import com.seolandfriends.byeolbyeolcoffee.report.command.application.dto.ReportReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.report.command.application.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
	private final ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService){
		this.reportService = reportService;
	}

	//새로운 신고 생성
	@PostMapping("/recipe")
	public ResponseEntity<ReportDTO> createRecipeReport(@RequestBody ReportDTO reportDTO) {
		ReportDTO reportDTo = reportService.createRecipeReport(reportDTO);
		return ResponseEntity.ok(reportDTo);
	}

	@PostMapping("/review")
	public ResponseEntity<ReportReviewDTO> createReviewReport(@RequestBody ReportReviewDTO reportDTO) {
		ReportReviewDTO reportDTo = reportService.createReportReview(reportDTO);
		return ResponseEntity.ok(reportDTo);
	}

	//신고 처리 수정(true/false)
	@PutMapping("/{reportId}")
	public ResponseEntity<ReportDTO> modifyRecipeReport(@PathVariable Long reportId, @RequestBody ReportDTO reportDTO){
		ReportDTO modifyreport = reportService.updateReport(reportId, reportDTO);
		return ResponseEntity.ok(modifyreport);
	}



}

