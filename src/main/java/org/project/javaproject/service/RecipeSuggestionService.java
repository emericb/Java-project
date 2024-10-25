package org.project.javaproject.service;

import org.project.javaproject.model.Recipe;
import org.project.javaproject.model.Product;
import org.project.javaproject.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeSuggestionService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeSuggestionService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> suggestRecipes(Set<Product> availableProducts) {
        return recipeRepository.findAll().stream()
                .filter(recipe -> availableProducts.containsAll(recipe.getProducts()))
                .collect(Collectors.toList());
    }
}
