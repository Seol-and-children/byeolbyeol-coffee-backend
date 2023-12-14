package com.seolandfriends.byeolbyeolcoffee.search.command.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
@Repository
public interface SearchRecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findByRecipeNameContaining(String query);
}
