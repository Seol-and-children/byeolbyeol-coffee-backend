package com.seolandfriends.byeolbyeolcoffee.report.query.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.report.command.application.dto.ReportDTO;
import com.seolandfriends.byeolbyeolcoffee.report.command.application.dto.ReportReviewDTO;
import com.seolandfriends.byeolbyeolcoffee.report.command.application.service.ReportService;
import com.seolandfriends.byeolbyeolcoffee.report.query.application.service.ReportQueryService;

@RestController
@RequestMapping("/reports")
public class ReportQueryController {

	private final ReportQueryService reportQueryService;

	@Autowired
	public ReportQueryController(ReportQueryService reportQueryService){
		this.reportQueryService = reportQueryService;
	}


	//모든 레시피 신고 불러오기
	@GetMapping("/recipes")
	public ResponseEntity<List<ReportDTO>> getAllReport(){
		List<ReportDTO> reportDTO = reportQueryService.getAllReport();
		return ResponseEntity.ok(reportDTO);
	}

	//모든 리뷰 신고 불러오기
	@GetMapping("/reviews")
	public ResponseEntity<List<ReportReviewDTO>> getAllReviewReport(){
		List<ReportReviewDTO> reportDTO = reportQueryService.getAllReviewReport();
		return ResponseEntity.ok(reportDTO);
	}


	//특정 신고 불러오기
	@GetMapping("/recipes/{reportId}")
	public ResponseEntity<ReportDTO> getReport(@PathVariable Long reportId) {
		ReportDTO reportDTO = reportQueryService.getReport(reportId);
		return ResponseEntity.ok(reportDTO);
	}

	//특정 신고 불러오기
	@GetMapping("/reviews/{reportId}")
	public ResponseEntity<ReportReviewDTO> getReviewReport(@PathVariable Long reportId) {
		ReportReviewDTO reportDTO = reportQueryService.getReviewReport(reportId);
		return ResponseEntity.ok(reportDTO);
	}
}
