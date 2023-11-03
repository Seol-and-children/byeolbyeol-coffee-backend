package com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class Franchise {
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
