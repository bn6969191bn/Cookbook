package com.example.cookbook.service;

import com.example.cookbook.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();

    Recipe getRecipeById(String id);

    Recipe addRecipe(Recipe recipe);

    void deleteRecipeById(String id);

    Recipe updateRecipe(String id, Recipe updatedRecipe);
}