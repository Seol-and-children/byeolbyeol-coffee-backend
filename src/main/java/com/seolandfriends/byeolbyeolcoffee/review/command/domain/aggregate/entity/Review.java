package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.ReviewUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	@Column(nullable = false)
	private String reviewName;

	@Embedded
	private ReviewUser author;

	@Column(nullable = false)
	private LocalDateTime registerTime;

	@Column(nullable = false)
	private Integer likesCount = 0;

	@Column(nullable = false)
	private Integer viewsCount = 0;

	@Lob
	private String content;

	@Column
	private String photoUrl;

	@PrePersist
	protected void onCreate() {
		registerTime = LocalDateTime.now();
	}

	@Builder
	public Review(String reviewName, ReviewUser author, LocalDateTime registerTime, String content, String photoUrl, Integer likesCount, Integer viewsCount) {
		this.reviewName = reviewName;
		this.author = author;
		this.registerTime = registerTime;
		this.content = content;
		this.photoUrl = photoUrl;
		this.likesCount = likesCount != null ? likesCount : this.likesCount;
		this.viewsCount = viewsCount != null ? viewsCount : this.viewsCount;

	}

	/* 리뷰 업데이트 메소드 */
	public void updateReview(String reviewName, ReviewUser author, LocalDateTime registerTime, String content, String photoUrl) {
		this.reviewName = reviewName;
		this.author = author;
		this.registerTime = registerTime;
		this.content = content;
		this.photoUrl = photoUrl;
		}


	/* 좋아요 수 증가 메소드 */
	public void incrementLikesCount() {
		this.likesCount++;
	}

	/* 조회수 증가 메소드 */
	public void incrementViewsCount() {
		this.viewsCount++;
	}

	/* 좋아요 수 감소 메소드 */
	public void decrementLikesCount() {
		if (this.likesCount > 0) {
			this.likesCount--;
		}
	}
}
