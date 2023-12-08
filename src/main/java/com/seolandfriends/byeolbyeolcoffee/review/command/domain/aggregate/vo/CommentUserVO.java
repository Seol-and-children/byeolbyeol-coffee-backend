package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ReviewCommentUserVO")
@Table(name = "user")
public class CommentUserVO {
	@Id
	@Column(name = "USER_ID")
	private String userId;
}