package com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report {
	@Id
	private Long reportId;
	private Long reportedId;
	private Long authorId;
	private String reportContent;
	private String reportTime;
	private String getReportTitle;

}
