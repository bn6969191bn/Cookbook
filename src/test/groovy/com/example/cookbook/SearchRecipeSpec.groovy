package com.example.cookbook

import com.example.cookbook.exceptions.RecipeNotFoundException
import com.example.cookbook.model.Recipe
import com.example.cookbook.repository.InMemoryRecipeRepository
import com.example.cookbook.service.serviceImpl.RecipeServiceImpl
import spock.lang.Specification

class SearchRecipeSpec extends Specification{

    def recipeService = new RecipeServiceImpl(new InMemoryRecipeRepository())

    def "should find recipe by name"() {
        given:
        def newRecipe = new Recipe("1","New Recipe", "New Description", "New Ingredients","New Instructions")
        recipeService.addRecipe(newRecipe)

        when:
        def result = recipeService.searchRecipesByName("New")

        then:
        result == [newRecipe]
    }

    def "should throw RecipeNotFoundException when no recipes found with name"() {

        when:
        recipeService.searchRecipesByName("New")

        then:
        thrown(RecipeNotFoundException)
    }
}
