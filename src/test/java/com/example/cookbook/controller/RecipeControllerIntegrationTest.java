package com.example.cookbook.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllRecipes() throws Exception {
        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].description").isNotEmpty());
    }

    @Test
    public void shouldReturnRecipeById() throws Exception {
        String recipeId = "655b621cd74f7e5ea2b67f47";

        mockMvc.perform(get("/api/recipes/" + recipeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.description").isNotEmpty())
                .andExpect(jsonPath("$.ingredients").isNotEmpty())
                .andExpect(jsonPath("$.instructions").isNotEmpty());
    }

    @Test
    public void shouldReturnNotFoundForNonExistingRecipe() throws Exception {
        String nonExistingRecipeId = "nonexistingid1234";

        mockMvc.perform(get("/api/recipes/" + nonExistingRecipeId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddNewRecipe() throws Exception {
        String newRecipeJson = """
        {
            "name": "Test Recipe",
            "description": "Test Description",
            "ingredients": "Test Ingredients",
            "instructions": "Test Instructions"
        }""";

        mockMvc.perform(post("/api/recipes")
                        .contentType("application/json")
                        .content(newRecipeJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Test Recipe"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.ingredients").value("Test Ingredients"))
                .andExpect(jsonPath("$.instructions").value("Test Instructions"));
    }

    @Test
    public void shouldNotAddRecipeWithMissingData() throws Exception {
        String newRecipeJson = """
        {
            "description": "Test Description",
            "ingredients": "Test Ingredients",
            "instructions": "Test Instructions"
        }"""; // brak pola "name"

        mockMvc.perform(post("/api/recipes")
                        .contentType("application/json")
                        .content(newRecipeJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldUpdateExistingRecipe() throws Exception {
        String recipeId = "657d847bf2bd793db12f277f";
        String updatedRecipeJson = """
        {
            "name": "Updated Recipe",
            "description": "Updated Description",
            "ingredients": "Updated Ingredients",
            "instructions": "Updated Instructions"
        }""";

        mockMvc.perform(put("/api/recipes/" + recipeId)
                        .contentType("application/json")
                        .content(updatedRecipeJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Updated Recipe"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.ingredients").value("Updated Ingredients"))
                .andExpect(jsonPath("$.instructions").value("Updated Instructions"));
    }

    @Test
    public void shouldReturnNotFoundWhenUpdatingNonexistentRecipe() throws Exception {
        String nonexistentRecipeId = "nonexistentRecipeId";
        String updatedRecipeJson = """
        {
            "name": "Updated Recipe",
            "description": "Updated Description",
            "ingredients": "Updated Ingredients",
            "instructions": "Updated Instructions"
        }""";

        mockMvc.perform(put("/api/recipes/" + nonexistentRecipeId)
                        .contentType("application/json")
                        .content(updatedRecipeJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundWhenDeletingNonexistentRecipe() throws Exception {
        String nonexistentRecipeId = "nonexistentRecipeId";

        mockMvc.perform(delete("/api/recipes/" + nonexistentRecipeId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnRecipesWhenSearchedByName() throws Exception {
        String searchName = "Spaghetti";

        mockMvc.perform(get("/api/recipes/search")
                        .param("name", searchName))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].description").isNotEmpty());
    }


}
