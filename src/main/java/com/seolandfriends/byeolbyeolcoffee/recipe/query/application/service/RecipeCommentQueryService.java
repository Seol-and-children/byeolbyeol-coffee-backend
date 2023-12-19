package com.seolandfriends.byeolbyeolcoffee.recipe.query.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeCommentDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeComment;
import com.seolandfriends.byeolbyeolcoffee.recipe.query.domain.repository.RecipeCommentQueryRepository;
@Service
public class RecipeCommentQueryService {
	private final RecipeCommentQueryRepository recipeCommentQueryRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public RecipeCommentQueryService(RecipeCommentQueryRepository recipeCommentQueryRepository) {
		this.recipeCommentQueryRepository = recipeCommentQueryRepository;
	}

	/* 댓글Id로 댓글 정보 가져오기 */
	@Transactional
	public RecipeCommentDto getRecipeCommentById(Long commentId) {
		RecipeComment comment = recipeCommentQueryRepository.findById(commentId)
			.orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. ID: " + commentId));

		RecipeCommentDto commentDto = modelMapper.map(comment, RecipeCommentDto.class);
		commentDto.setUserNickname(comment.getCommentUserVO().getUserNickname());
		return commentDto;
	}

	/* 특정 레시피의 모든 댓글 가져오기 */
	@Transactional
	public List<RecipeCommentDto> getAllRecipeComments(Long recipeId) {
		List<RecipeComment> comments = recipeCommentQueryRepository.findByRecipe_RecipeId(recipeId);
		return comments.stream()
			.map(comment -> {
				RecipeCommentDto commentDto = modelMapper.map(comment, RecipeCommentDto.class);
				commentDto.setUserNickname(comment.getCommentUserVO().getUserNickname());
				return commentDto;
			})
			.collect(Collectors.toList());
	}
}
