package org.project.javaproject.controller;

import lombok.Getter;
import lombok.Setter;
import org.project.javaproject.model.Product;

import java.util.Set;

@Setter
@Getter
public class RecipeWithIngredientsRequest {
    private String name;
    private String description;
    private int servings;
    private Set<String> productCodes;
}
