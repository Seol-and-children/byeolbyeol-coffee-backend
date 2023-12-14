package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity;

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

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.ReviewUserVO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long reviewId;

	@Column(name = "name", nullable = false, length = 128)
	private String reviewName;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private ReviewUserVO author;

	@Column(name = "content", length = 255)
	private String content;

	@Column(name = "photo")
	private String photoUrl;

	@Column(name = "register_time")
	private LocalDateTime registerTime;

	@Column(name = "likes_count", nullable = false)
	private Integer likesCount = 0;

	@Column(name = "views_count", nullable = false)
	private Integer viewsCount = 0;

	@PrePersist
	protected void onCreate() {
		registerTime = LocalDateTime.now();
	}

	@Builder(toBuilder = true)
	public Review(String reviewName, String photoUrl, String content,
		ReviewUserVO author, Integer likesCount, Integer viewsCount) {
		this.reviewName = reviewName;
		this.photoUrl = photoUrl;
		this.content = content;
		this.author = author;
		this.likesCount = likesCount != null ? likesCount : this.likesCount;
		this.viewsCount = viewsCount != null ? viewsCount : this.viewsCount;
	}

	public Review reviewImageUrl(String photoUrl) {
		this.photoUrl = photoUrl;
		return this;
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