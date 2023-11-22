package com.seolandfriends.byeolbyeolcoffee.admin.command.application.controller;

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

import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.ReportDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.application.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
	private final ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService){
		this.reportService = reportService;
	}

	//새로운 신고 생성
	@PostMapping
	public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {
		ReportDTO reportDTo = reportService.createReport(reportDTO);
		return ResponseEntity.ok(reportDTo);
	}

	//신고 처리 수정(true/false)
	@PutMapping("/{reportId}")
	public ResponseEntity<ReportDTO> modifyReport(@PathVariable Long reportId, @RequestBody ReportDTO reportDTO){
		ReportDTO modifyreport = reportService.updateReport(reportId, reportDTO);
		return ResponseEntity.ok(modifyreport);
	}

	//모든 신고 불러오기
	@GetMapping
	public ResponseEntity<List<ReportDTO>> getAllReport(){
		List<ReportDTO> reoprtDTO = reportService.getAllReport();
		return ResponseEntity.ok(reoprtDTO);
	}

	//특정 신고 불러오기
	@GetMapping("/{reportId}")
	public ResponseEntity<ReportDTO> getRepoert(@PathVariable Long reportId) {
		ReportDTO reportDTO = reportService.getReport(reportId);
		return ResponseEntity.ok(reportDTO);
	}

}

