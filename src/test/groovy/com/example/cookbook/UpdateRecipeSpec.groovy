package com.example.cookbook

import com.example.cookbook.exceptions.RecipeNotFoundException
import com.example.cookbook.model.Recipe
import com.example.cookbook.repository.InMemoryRecipeRepository
import com.example.cookbook.service.serviceImpl.RecipeServiceImpl
import spock.lang.Specification

class UpdateRecipeSpec extends Specification {

    def recipeService = new RecipeServiceImpl(new InMemoryRecipeRepository())

    def "should update existing recipe"() {
        given:
        def existingRecipe = new Recipe(id: "1", name: "Old Recipe", description: "Old Description", ingredients: "Old Ingredients", instructions: "Old Instructions")
        def updatedRecipe = new Recipe(id: "1", name: "Updated Recipe", description: "Updated Description", ingredients: "Updated Ingredients", instructions: "Updated Instructions")
        recipeService.addRecipe(existingRecipe)

        when:
        def result = recipeService.updateRecipe("1", updatedRecipe)

        then:
        result == updatedRecipe
    }

    def "should throw RecipeNotFoundException when updating non-existent recipe"() {
        given:
        def updatedRecipe = new Recipe(id: "2", name: "Updated Recipe", description: "Updated Description", ingredients: "Updated Ingredients", instructions: "Updated Instructions")

        when:
        recipeService.updateRecipe("2", updatedRecipe)

        then:
        thrown(RecipeNotFoundException)
    }
}
