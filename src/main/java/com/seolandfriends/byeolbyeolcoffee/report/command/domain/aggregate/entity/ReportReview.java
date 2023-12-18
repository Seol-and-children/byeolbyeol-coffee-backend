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

import org.hibernate.annotations.Cascade;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="report_review")
@NoArgsConstructor
public class ReportReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long reportId;
	@Column(name = "report_category")
	private String reportCategory;
	@Column(name = "report_reason")
	private String reportReason;
	@Column(name = "author_name")
	private String authorName;
	@Column(name = "report_time")
	private LocalDateTime reportTime;
	@Column(name = "process")
	private boolean processing;
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.REMOVE)
	@JoinColumn(name = "report_review_id") // Recipe 엔터티의 기본 키를 참조
	private Review review;


	@PrePersist
	protected void onCreate() {
		reportTime = LocalDateTime.now();
	}

	@Builder
	public ReportReview(String reportCategory, String reportReason, String authorName, boolean processing, Review review){
		this.reportCategory = reportCategory;
		this.reportReason = reportReason;
		this.authorName = authorName;
		this.processing = processing;
		this.review = review;
	}

	//처리 여부 변경 메소드
	public void processingCompleted(){ this.processing = false;	}
	public void processingBefore(){
		this.processing = true;
	}
}