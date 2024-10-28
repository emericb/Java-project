package org.project.javaproject.service;

import org.project.javaproject.model.Recipe;
import org.project.javaproject.repository.RecipeRepository;
import org.project.javaproject.controller.AdjustedRecipeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> updateRecipe(Long id, Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setName(updatedRecipe.getName());
            recipe.setDescription(updatedRecipe.getDescription());
            recipe.setProducts(updatedRecipe.getProducts());
            return recipeRepository.save(recipe);
        });
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> suggestRecipes(List<String> ingredients) {
        return recipeRepository.findByIngredients(ingredients);
    }

    public Optional<AdjustedRecipeResponse> adjustRecipeServings(Long id, int servings) {
        Optional<Recipe> recipeOpt = getRecipeById(id);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            List<AdjustedRecipeResponse.ProductQuantity> adjustedProducts = recipe.getProducts().stream()
                    .map(product -> {
                        int adjustedQuantity = product.getQuantity() * servings / recipe.getServings();
                        return new AdjustedRecipeResponse.ProductQuantity(product.getName(), adjustedQuantity);
                    })
                    .collect(Collectors.toList());
            recipe.setServings(servings);
            return Optional.of(new AdjustedRecipeResponse(recipe.getName(), adjustedProducts, servings));
        }
        return Optional.empty();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }
}
