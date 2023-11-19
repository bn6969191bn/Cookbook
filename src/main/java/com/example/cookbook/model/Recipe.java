package com.example.cookbook.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String ingredients;
    @NotBlank
    private String instructions;

}
