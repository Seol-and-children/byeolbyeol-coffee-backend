package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CommentUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class RecipeComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	@Embedded
	private CommentUser commentUser;

	@Column
	private String content;

	@ManyToOne
	@JoinColumn
	private RecipeComment parent;

	@Column
	private int depth;

	@ManyToOne
	@JoinColumn
	private Recipe recipe;

	@Builder
	public RecipeComment(CommentUser commentUser, String content,
		RecipeComment parent, int depth, Recipe recipe) {
		this.commentUser = commentUser;
		this.content = content;
		this.parent = parent;
		this.depth = depth;
		this.recipe = recipe;
	}

	/* 댓글 내용 수정 메소드 */
	public void updateContent(String content) {
		this.content = content;
	}
}