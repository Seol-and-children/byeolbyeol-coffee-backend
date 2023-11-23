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
@Table(name = "review")

public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	@Column(name = "name", nullable = false)
	private String reviewName;

	@Embedded
	private ReviewUser author;

	@Column(name = "register_time", nullable = false)
	private LocalDateTime registerTime;

	@Column(name = "likes_count", nullable = false)
	private Integer likesCount = 0;

	@Column(name = "views_count", nullable = false)
	private Integer viewsCount = 0;

	@Lob
	@Column(name = "content")
	private String content;

	@Column(name = "photo")
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
