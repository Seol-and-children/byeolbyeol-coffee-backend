package com.seolandfriends.byeolbyeolcoffee.report.command.domain.aggregate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="report")
@NoArgsConstructor
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long reportId;
	@Column(name = "report_category")
	private String reportCategory;
	@Column(name = "report_reason")
	private String reportReason;
	@Column(name = "authoor_name")
	private String authorName;
	@Column(name = "report_time")
	private LocalDateTime reportTime;
	@Column(name = "process")
	private boolean processing;
	@ManyToOne
	@JoinColumn(name = "report_recipe_id") // Recipe 엔터티의 기본 키를 참조
	private Recipe recipe;

	@PrePersist
	protected void onCreate() {
		reportTime = LocalDateTime.now();
	}

	@Builder
	public Report(String reportCategory, String reportReason, String authorName, boolean processing, Recipe recipe){
		this.reportCategory = reportCategory;
		this.reportReason = reportReason;
		this.authorName = authorName;
		this.processing = processing;
		this.recipe = recipe;
	}

	//처리 여부 변경 메소드
	public void processingCompleted(){ this.processing = false;	}
	public void processingBefore(){
		this.processing = true;
	}
}