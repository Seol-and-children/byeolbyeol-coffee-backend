package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeCommentDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeComment;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CommentUserVO;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeCommentCommandRepository;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeCommandRepository;

@Service
public class RecipeCommentCommandService {
	private final RecipeCommentCommandRepository recipeCommentCommandRepository;
	private final RecipeCommandRepository recipeCommandRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public RecipeCommentCommandService(RecipeCommentCommandRepository recipeCommentCommandRepository,
		RecipeCommandRepository recipeCommandRepository) {
		this.recipeCommentCommandRepository = recipeCommentCommandRepository;
		this.recipeCommandRepository = recipeCommandRepository;
	}

	@Transactional
	public RecipeCommentDto createRecipeComment(RecipeCommentDto recipeCommentDto, Long recipeId) {
		Recipe recipe = recipeCommandRepository.findById(recipeId)
			.orElseThrow(() -> new EntityNotFoundException("레시피를 찾을 수 없습니다."));

		RecipeComment parentComment = null;
		if (recipeCommentDto.getParentId() != null) {
			parentComment = recipeCommentCommandRepository.findById(recipeCommentDto.getParentId())
				.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

			// 원 댓글에만 답글을 달 수 있도록 검증
			if (parentComment.getDepth() != 0) {
				throw new IllegalStateException("답글에는 답글을 달 수 없습니다.");
			}
		}

		// 답글의 경우 깊이를 1로 설정
		int depth = (parentComment != null) ? 1 : 0;

		CommentUserVO commentUserVO = new CommentUserVO(recipeCommentDto.getUserId());

		RecipeComment newComment = RecipeComment.builder()
			.commentUserVO(commentUserVO)
			.content(recipeCommentDto.getContent())
			.parent(parentComment)
			.depth(depth)
			.recipe(recipe)
			.build();

		RecipeComment savedComment = recipeCommentCommandRepository.save(newComment);
		return modelMapper.map(savedComment, RecipeCommentDto.class);
	}

	/* 댓글 수정 메소드 */
	@Transactional
	public RecipeCommentDto updateRecipeComment(Long commentId, RecipeCommentDto recipeCommentDto, Long recipeId) {
		RecipeComment comment = recipeCommentCommandRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		if (!comment.getRecipe().getRecipeId().equals(recipeId)) {
			throw new SecurityException("이 레시피의 댓글이 아닙니다.");
		}

		comment.updateContent(recipeCommentDto.getContent());

		RecipeComment updatedComment = recipeCommentCommandRepository.save(comment);
		return modelMapper.map(updatedComment, RecipeCommentDto.class);
	}

	/* 댓글 삭제 메소드 */
	@Transactional
	public void deleteRecipeComment(Long recipeId, Long commentId) {
		RecipeComment comment = recipeCommentCommandRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		if (!comment.getRecipe().getRecipeId().equals(recipeId)) {
			throw new SecurityException("이 레시피의 댓글이 아닙니다.");
		}

		// 댓글이 원 댓글인 경우
		if (comment.getDepth() == 0) {
			long repliesCount = recipeCommentCommandRepository.countByParentCommentId(commentId);

			// 답글이 있는 원 댓글인 경우 내용만 변경
			if (repliesCount > 0) {
				comment.updateContent("(삭제된 댓글입니다)");
				recipeCommentCommandRepository.save(comment);
			} else {
				// 답글이 없는 원 댓글은 삭제
				recipeCommentCommandRepository.delete(comment);
			}
		} else {
			// 댓글이 답글인 경우
			RecipeComment parentComment = comment.getParent();
			recipeCommentCommandRepository.delete(comment);

			// 원 댓글이 "(삭제된 댓글입니다)" 상태이고 다른 답글이 없는 경우 원 댓글도 삭제
			if (parentComment != null && "(삭제된 댓글입니다)".equals(parentComment.getContent())) {
				long repliesCount = recipeCommentCommandRepository.countByParentCommentId(parentComment.getCommentId());
				if (repliesCount == 0) {
					recipeCommentCommandRepository.delete(parentComment);
				}
			}
		}
	}
}
