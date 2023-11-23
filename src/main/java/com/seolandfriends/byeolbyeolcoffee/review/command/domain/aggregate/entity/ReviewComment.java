package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.CommentUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class ReviewComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	@Embedded
	private CommentUser commentUser;

	@Column
	private String content;

	@ManyToOne
	@JoinColumn
	private ReviewComment parent;

	@Column
	private int depth;

	@ManyToOne
	@JoinColumn
	private Review review;

	@Builder
	public ReviewComment(CommentUser commentUser, String content,
		ReviewComment parent, int depth, Review review) {
		this.commentUser = commentUser;
		this.content = content;
		this.parent = parent;
		this.depth = depth;
		this.review = review;
	}

	/* 댓글 내용 수정 메소드 */
	public void updateContent(String content) {
		this.content = content;
	}
}