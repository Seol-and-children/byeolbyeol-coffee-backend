package com.seolandfriends.byeolbyeolcoffee.report.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {
	private Long reportId;

	private String reportedName;

	private String reportCategory;

	private String authorName;

	private String reportReason;

	private String reportedContent;

	private String reportTime;

	private String contentTitle;

	private boolean processing;

	private Long recipeId;
}
