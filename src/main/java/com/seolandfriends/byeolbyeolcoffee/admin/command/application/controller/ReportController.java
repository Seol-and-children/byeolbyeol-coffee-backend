package com.seolandfriends.byeolbyeolcoffee.admin.command.application.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.ReportDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.application.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
	private final ReportService reportService;
	private final ModelMapper modelMapper;
	@Autowired
	public ReportController(ReportService reportService, ModelMapper modelMapper){
		this.reportService = reportService;
		this.modelMapper = modelMapper;
	}

	//새로운 신고 생성
	@PostMapping
	public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {
		ReportDTO reportDTo = reportService.createReport(reportDTO);
		return ResponseEntity.ok(reportDTo);
	}

	//신고 처리 수정(true/false)
	@PutMapping("{report_id}}")
	public ResponseEntity<ReportDTO> updateReport(@PathVariable Long reportId, @RequestBody boolean process){
		ReportDTO reportDTO = reportService.updateReport(reportId, process);
		return ResponseEntity.ok(reportDTO);
	}
	// public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {
	// 	Report report = reportService.createReport(reportDTO);
	// 	ReportDTO reportDto = modelMapper.map(report, ReportDTO.class);
	// 	return ResponseEntity.ok(reportDto);
	//
	// @GetMapping("{report_id}}")
	// public
	//
	// @GetMapping

}

