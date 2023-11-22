package com.seolandfriends.byeolbyeolcoffee.admin.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.Report;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository.SearchRepository;

@Service
public class SearchService {
	private final SearchRepository searchRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public SearchService(SearchRepository searchRepository, ModelMapper modelMapper) {
		this.searchRepository = searchRepository;
		this.modelMapper = modelMapper;
	}

	//검색기능
	public List<Report> search(String query) {
		List<Report> report = searchRepository.findByreportedContentContaining(query);
		return report;
	}
}
