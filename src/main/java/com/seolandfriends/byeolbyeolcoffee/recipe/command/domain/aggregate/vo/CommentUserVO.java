package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class CommentUserVO {
	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "user_nickname")
	private String userNickname;

	public CommentUserVO(String userId) {
		this.userId = userId;
	}
}