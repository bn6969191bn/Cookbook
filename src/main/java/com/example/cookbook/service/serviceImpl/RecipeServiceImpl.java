package com.example.cookbook.service.serviceImpl;

import com.example.cookbook.exceptions.RecipeNotFoundException;
import com.example.cookbook.model.Recipe;
import com.example.cookbook.repository.RecipeRepository;
import com.example.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        try {
            return recipeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all recipes", e);
        }
    }

    @Override
    public Recipe getRecipeById(String id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("Recipe with ID " + id + " not found"));
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (recipe.getName() == null || recipe.getName().isEmpty() ||
                recipe.getDescription() == null || recipe.getDescription().isEmpty() ||
                recipe.getIngredients() == null || recipe.getIngredients().isEmpty() ||
                recipe.getInstructions() == null || recipe.getInstructions().isEmpty()) {
            throw new IllegalArgumentException("All fields (name, description, ingredients, instructions) are required");
        }

        try {
            return recipeRepository.save(recipe);
        } catch (Exception e) {
            throw new RuntimeException("Error adding a new recipe", e);
        }
    }

    @Override
    public void deleteRecipeById(String id) {
        try {
            recipeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecipeNotFoundException("Recipe with ID " + id + " not found");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting recipe with ID " + id, e);
        }
    }

    @Override
    public Recipe updateRecipe(String id, Recipe updatedRecipe) {
        Recipe existingRecipe = validateAndGetExistingRecipe(id);

        if (updatedRecipe.getName() != null) {
            existingRecipe.setName(updatedRecipe.getName());
        }
        if (updatedRecipe.getDescription() != null) {
            existingRecipe.setDescription(updatedRecipe.getDescription());
        }
        if (updatedRecipe.getIngredients() != null) {
            existingRecipe.setIngredients(updatedRecipe.getIngredients());
        }
        if (updatedRecipe.getInstructions() != null) {
            existingRecipe.setInstructions(updatedRecipe.getInstructions());
        }

        return recipeRepository.save(existingRecipe);
    }

    private Recipe validateAndGetExistingRecipe(String id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with ID " + id + " not found"));
    }

    @Override
    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name);
    }
}
