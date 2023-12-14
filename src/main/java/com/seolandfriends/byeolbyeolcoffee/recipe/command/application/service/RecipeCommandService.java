package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeCustomOption;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.FranchiseCafeVO;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.RecipeUserVO;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeCommandRepository;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeCustomOptionCommandRepository;
import com.seolandfriends.byeolbyeolcoffee.util.FileUploadUtils;

@Service
public class RecipeCommandService {
	private final RecipeCommandRepository recipeCommandRepository;
	private final RecipeCustomOptionCommandRepository customOptionRepository;
	ModelMapper modelMapper = new ModelMapper();

	/* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	@Value("${image.image-url}")
	private String IMAGE_URL;

	@Autowired
	public RecipeCommandService(RecipeCommandRepository recipeCommandRepository,
		RecipeCustomOptionCommandRepository customOptionRepository) {
		this.recipeCommandRepository = recipeCommandRepository;
		this.customOptionRepository = customOptionRepository;
	}

	/* 새로운 레시피 생성 메소드 */
	@Transactional
	public RecipeDto createRecipe(RecipeDto recipeDto, MultipartFile recipeImage) {
		String imageName = UUID.randomUUID().toString().replace("-", "");
		String replaceFileName = null;

		try {
			replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, recipeImage);
			recipeDto.setPhotoUrl(replaceFileName);

			RecipeUserVO author = new RecipeUserVO(recipeDto.getAuthorId());
			FranchiseCafeVO franchiseCafeVO = new FranchiseCafeVO(recipeDto.getFranchiseId());

			Recipe newRecipe = Recipe.builder()
				.recipeName(recipeDto.getRecipeName())
				.photoUrl(recipeDto.getPhotoUrl())
				.description(recipeDto.getDescription())
				.franchiseCafeVO(franchiseCafeVO)
				.baseBeverageVO(recipeDto.getBaseBeverageVO())
				.author(author)
				.likesCount(0)
				.viewsCount(0)
				.build();

			Recipe savedRecipe = recipeCommandRepository.save(newRecipe);

			List<RecipeCustomOption> customOptions = recipeDto.getCustomOptions().stream()
				.map(dto -> new RecipeCustomOption(dto.getCustomOptionName(), dto.getQuantity(), savedRecipe.getRecipeId()))
				.collect(Collectors.toList());

			customOptionRepository.saveAll(customOptions);

			return modelMapper.map(savedRecipe, RecipeDto.class);
		} catch (Exception e) {
			FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			throw new RuntimeException(e);
		}
	}

	/* {recipeid}를 가진 레시피 정보 수정하기 */
	@Transactional
	public RecipeDto updateRecipe(Long recipeId, RecipeDto recipeDto, MultipartFile recipeImage) {
		String replaceFileName = null;

		try {
			Recipe existingRecipe = recipeCommandRepository.findById(recipeId)
				.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));

			// 이미지 업데이트 처리
			if (recipeImage != null && !recipeImage.isEmpty()) {
				String imageName = UUID.randomUUID().toString().replace("-", "");
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, recipeImage);
				recipeDto.setPhotoUrl(replaceFileName);
			} else {
				recipeDto.setPhotoUrl(existingRecipe.getPhotoUrl());
			}

			Recipe updatedRecipe = Recipe.builder()
				.recipeName(recipeDto.getRecipeName())
				.photoUrl(recipeDto.getPhotoUrl())
				.description(recipeDto.getDescription())
				.franchiseCafeVO(new FranchiseCafeVO(recipeDto.getFranchiseId()))
				.baseBeverageVO(recipeDto.getBaseBeverageVO())
				.build();

			Recipe savedRecipe = recipeCommandRepository.save(updatedRecipe);

			List<RecipeCustomOption> customOptions = recipeDto.getCustomOptions().stream()
				.map(dto -> new RecipeCustomOption(dto.getCustomOptionName(), dto.getQuantity(), savedRecipe.getRecipeId()))
				.collect(Collectors.toList());

			customOptionRepository.saveAll(customOptions);

			return modelMapper.map(savedRecipe, RecipeDto.class);
		} catch (IOException e) {
			FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			throw new RuntimeException(e);
		}
	}

	/* {recipeId}를 가진 레시피 삭제하기 */
	public void deleteRecipe(Long recipeId) {
		if (!recipeCommandRepository.existsById(recipeId)) {
			throw new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId);
		}
		recipeCommandRepository.deleteById(recipeId);
	}

}