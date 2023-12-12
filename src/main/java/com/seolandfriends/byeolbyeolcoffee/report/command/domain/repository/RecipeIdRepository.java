package com.seolandfriends.byeolbyeolcoffee.report.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;

@Repository
public interface RecipeIdRepository extends JpaRepository<Recipe, Long> {

}