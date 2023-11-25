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
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CustomOption;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.FranchiseCafe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.RecipeUser;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeRepository;
import com.seolandfriends.byeolbyeolcoffee.util.FileUploadUtils;

@Service
public class RecipeService {
	private final RecipeRepository recipeRepository;
	ModelMapper modelMapper = new ModelMapper();

	/* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	@Value("${image.image-url}")
	private String IMAGE_URL;

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	/* 새로운 레시피 생성 메소드 */
	@Transactional
	public RecipeDto createRecipe(RecipeDto recipeDto, MultipartFile recipeImage) {
		String imageName = UUID.randomUUID().toString().replace("-", "");
		String replaceFileName = null;

		try {
			replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, recipeImage);

			recipeDto.setPhotoUrl(replaceFileName);
			FranchiseCafe franchiseCafe = new FranchiseCafe(recipeDto.getFranchiseId());
			CustomOption customOption = new CustomOption(recipeDto.getCustomOptionId());
			RecipeUser author = new RecipeUser(recipeDto.getAuthorId());

			Recipe newRecipe = Recipe.builder()
				.recipeName(recipeDto.getRecipeName())
				.photoUrl(recipeDto.getPhotoUrl())
				.description(recipeDto.getDescription())
				.franchiseCafe(franchiseCafe)
				.author(author)
				.baseBeverage(recipeDto.getBaseBeverage())
				.customOptions(customOption)
				.likesCount(0)
				.viewsCount(0)
				.build();

			Recipe savedRecipe = recipeRepository.save(newRecipe);
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
			Recipe recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));
			String oriImage = recipe.getPhotoUrl();

			FranchiseCafe franchiseCafe = new FranchiseCafe(recipeDto.getFranchiseId());
			CustomOption customOption = new CustomOption(recipeDto.getCustomOptionId());

			recipe.updateRecipe(
				recipeDto.getRecipeName(),
				recipeDto.getPhotoUrl(),
				recipeDto.getDescription(),
				franchiseCafe,
				recipeDto.getBaseBeverage(),
				customOption
			);

			if (recipeImage != null) {
				String imageName = UUID.randomUUID().toString().replace("-", "");
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, recipeImage);

				recipe = recipe.recipeImageUrl(replaceFileName);

			} else {
				/* 이미지 변경 없을 시 */
				recipe = recipe.recipeImageUrl(oriImage);
			}

			Recipe savedRecipe = recipeRepository.save(recipe);
			return modelMapper.map(savedRecipe, RecipeDto.class);
		} catch (IOException e) {
			FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			throw new RuntimeException(e);
		}
	}

	/* 모든 레시피 정보 불러오기 */
	public List<RecipeDto> getAllRecipes() {
		List<Recipe> savedRecipes = recipeRepository.findAll();
		return savedRecipes.stream()
			.map(recipe -> modelMapper.map(recipe, RecipeDto.class))
			.collect(Collectors.toList());
	}

	/* {recipeId}를 가진 레시피 정보 불러오기 */
	public RecipeDto getRecipeById(Long recipeId) {
		Recipe savedRecipe = recipeRepository.findById(recipeId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));
		savedRecipe.incrementViewsCount();
		savedRecipe = recipeRepository.save(savedRecipe);
		return modelMapper.map(savedRecipe, RecipeDto.class);
	}

	/* {recipeId}를 가진 레시피 삭제하기 */
	public void deleteRecipe(Long recipeId) {
		if (!recipeRepository.existsById(recipeId)) {
			throw new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId);
		}
		recipeRepository.deleteById(recipeId);
	}

}