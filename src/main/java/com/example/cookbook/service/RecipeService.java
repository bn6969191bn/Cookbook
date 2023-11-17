package com.example.cookbook.service;

import com.example.cookbook.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();

    Recipe getRecipeById(String id);
}