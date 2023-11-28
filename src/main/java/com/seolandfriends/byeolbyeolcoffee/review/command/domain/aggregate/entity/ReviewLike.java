package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.LikeUserVO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "review_like")
public class ReviewLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private LikeUserVO likeUserVO;

	@Builder
	public ReviewLike(Review review, LikeUserVO likeUserVO) {
		this.review = review;
		this.likeUserVO = likeUserVO;
	}
}