package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CommentUserVO;

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
	@Column(name = "id", nullable = false)
	private Long commentId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private CommentUserVO commentUserVO;

	@Column(name = "content", length = 255)
	private String content;

	@ManyToOne
	@JoinColumn(name = "parent_comment_id")
	private RecipeComment parent;

	@Column(name = "depth", nullable = false)
	private int depth;

	@ManyToOne
	@JoinColumn(name = "recipe_id", nullable = false)
	private Recipe recipe;

	@Builder
	public RecipeComment(CommentUserVO commentUserVO, String content,
		RecipeComment parent, int depth, Recipe recipe) {
		this.commentUserVO = commentUserVO;
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