package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity;

	import javax.persistence.Embedded;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;

	import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.LikeUser;

	import lombok.Builder;
	import lombok.Getter;
	import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class ReviewLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	private Review review;

	@Embedded
	private LikeUser likeUser;

	@Builder
	public ReviewLike(Review review, LikeUser likeUser) {
		this.review = review;
		this.likeUser = likeUser;
	}
}