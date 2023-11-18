package com.example.cookbook.service.serviceImpl;

import com.example.cookbook.exceptions.RecipeNotFoundException;
import com.example.cookbook.model.Recipe;
import com.example.cookbook.repository.RecipeRepository;
import com.example.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(String id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipeById(String id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(String id, Recipe updatedRecipe) {
        Optional<Recipe> existingRecipeOptional = recipeRepository.findById(id);

        if (existingRecipeOptional.isPresent()) {
            Recipe existingRecipe = existingRecipeOptional.get();

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

        throw new RecipeNotFoundException("Recipe with ID " + id + " not found");
    }


}

