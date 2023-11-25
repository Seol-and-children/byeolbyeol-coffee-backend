package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CommentUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "recipe_comment")
public class RecipeComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long commentId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private CommentUser commentUser;

	@Column(name = "content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "parent_comment_id")
	private RecipeComment parent;

	@Column(name = "depth")
	private int depth;

	@ManyToOne
	@JoinColumn(name = "recipe_id")
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