package com.example.cookbook

import com.example.cookbook.model.Recipe
import com.example.cookbook.repository.InMemoryRecipeRepository
import com.example.cookbook.service.serviceImpl.RecipeServiceImpl
import spock.lang.Specification
import spock.lang.Unroll

class AddRecipeSpec extends Specification {

    def recipeService = new RecipeServiceImpl(new InMemoryRecipeRepository())

    def "should add new recipe"() {
        given:
        def newRecipe = new Recipe("id","New Recipe", "New Description", "New Ingredients","New Instructions")

        when:
        def result = recipeService.addRecipe(newRecipe)

        then:
        result == newRecipe
    }

    @Unroll
    def "should throw IllegalArgumentException when adding recipe with empty fields or null values"() {
        given:
        def newRecipe = new Recipe(ID, NAME, DESCRIPTION, INGREDIENTS, INSTRUCTIONS)

        when:
        recipeService.addRecipe(newRecipe)

        then:
        thrown(IllegalArgumentException)

        where:
        ID | NAME | DESCRIPTION | INGREDIENTS | INSTRUCTIONS
        "1" | null | "description" | "" | "instructions"
        "2" | "name" | "description" | null | "instructions"
        "3" | "name" | null | "ingredients" | "instructions"
        "4" | "name" | "" | "ingredients" | null
        null | null | null | null | null

    }
}
