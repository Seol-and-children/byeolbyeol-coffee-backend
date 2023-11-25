package com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Option {
	@Id
	@Column(name = "report_id")
	private Long reportId;
	@Column(name = "reported_name")
	private String reportedName;
	@Column(name = "author_name")
	private String authorName;
	@Column(name = "report_reason")
	private String reportReason;
	@Column(name = "reported_content")
	private String reportedContent;
	@Column(name = "report_time")
	private String reportTime;
	@Column(name = "content_title")
	private String contentTitle;
	@Column(name = "process")
	private boolean processing;



}
