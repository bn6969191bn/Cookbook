package com.example.cookbook.controller;

import com.example.cookbook.exceptions.RecipeNotFoundException;
import com.example.cookbook.model.Recipe;
import com.example.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRecipes() {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();

            if (recipes.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No recipes found");
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(recipes);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving recipes");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable String id) {
        try {
            Recipe recipe = recipeService.getRecipeById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(recipe);
        } catch (RecipeNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Recipe not found");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving recipe");
        }
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        try {
            Recipe addedRecipe = recipeService.addRecipe(recipe);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(addedRecipe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding a new recipe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String id) {
        try {
            if (recipeService.getRecipeById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");
            }

            recipeService.deleteRecipeById(id);
            return ResponseEntity.ok("Recipe deleted successfully");
        } catch (RecipeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting recipe");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable String id, @RequestBody Recipe updatedRecipe) {
        try {
            Recipe updated = recipeService.updateRecipe(id, updatedRecipe);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updated);
        } catch (RecipeNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Recipe not found");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating recipe");
        }
    }
}
