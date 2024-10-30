package org.project.javaproject.service;

import org.project.javaproject.controller.RecipeWithIngredientsRequest;
import org.project.javaproject.model.Product;
import org.project.javaproject.model.Recipe;
import org.project.javaproject.repository.ProductRepository;
import org.project.javaproject.repository.RecipeRepository;
import org.project.javaproject.controller.AdjustedRecipeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;

    public RecipeService(RecipeRepository recipeRepository, ProductRepository productRepository) {
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
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
        Set<String> ingredientSet = Set.copyOf(ingredients);
        return recipeRepository.findAll().stream()
                .filter(recipe -> recipe.getProducts().stream()
                        .allMatch(product -> ingredientSet.contains(product.getName())))
                .collect(Collectors.toList());
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
            int totalCalories = recipe.getProducts().stream()
                    .mapToInt(product -> (int) (product.getEnergyKcal() * product.getQuantity()))
                    .sum();
            int newCaloriesPerServing = totalCalories / servings;
            Set<String> allergens = recipe.getProducts().stream()
                    .flatMap(product -> product.getAllergens().stream())
                    .collect(Collectors.toSet());
            recipe.setServings(servings);
            return Optional.of(new AdjustedRecipeResponse(recipe.getName(), adjustedProducts, servings, newCaloriesPerServing, allergens));
        }
        return Optional.empty();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe createRecipeWithIngredients(RecipeWithIngredientsRequest request) {
        Recipe recipe = new Recipe();
        recipe.setName(request.getName());
        recipe.setDescription(request.getDescription());
        recipe.setServings(request.getServings());

        Recipe savedRecipe = recipeRepository.save(recipe);

        Set<String> productCodes = request.getProductCodes() != null ? new HashSet<>(request.getProductCodes()) : new HashSet<>();
        for (String productCode : productCodes) {
            recipeRepository.addProductToRecipe(savedRecipe.getId(), productCode);
        }

        return savedRecipe;
    }
}
