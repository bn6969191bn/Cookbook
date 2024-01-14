package com.example.cookbook.repository;

import com.example.cookbook.model.Recipe;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryRecipeRepository implements RecipeRepository{
    Map<String, Recipe> values = new HashMap<>();
    @Override
    public List<Recipe> findByNameContainingIgnoreCase(String name) {
        return values.values().stream().filter(recipe -> recipe.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public <S extends Recipe> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Recipe> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Recipe> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Recipe> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Recipe> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Recipe> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Recipe> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Recipe> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Recipe, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public  Recipe save(Recipe entity) {
        if (entity.getId() == null) {
            entity = entity.toBuilder()
                    .id("id")
                    .build();
        }
        values.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Recipe> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Recipe> findById(String s) {
        return values.values().stream().filter(recipe -> recipe.getId().equals(s)).findFirst();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Recipe> findAll() {
        return null;
    }

    @Override
    public List<Recipe> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Recipe entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Recipe> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Recipe> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Recipe> findAll(Pageable pageable) {
        return null;
    }

}
