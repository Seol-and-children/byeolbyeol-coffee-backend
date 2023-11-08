package com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.ReviewComment;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.CommentUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentDTO {
	private Long commentId;
	private CommentUser commentUser;
	private String content;
	private ReviewComment parent;
	private int depth;
}