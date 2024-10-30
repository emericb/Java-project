package org.project.javaproject.controller;

import org.project.javaproject.model.Recipe;
import org.project.javaproject.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        Optional<Recipe> updated = recipeService.updateRecipe(id, updatedRecipe);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/suggest")
    public ResponseEntity<?> suggestRecipes(@RequestBody List<String> ingredients) {
        List<Recipe> recipes = recipeService.suggestRecipes(ingredients);
        if (recipes.isEmpty()) {
            return ResponseEntity.ok().body("{\"message\": \"No recipes found\"}");
        }
        return ResponseEntity.ok(recipes);
    }

    @PutMapping("/{id}/servings/{servings}")
    public ResponseEntity<AdjustedRecipeResponse> adjustRecipeServings(@PathVariable Long id, @PathVariable int servings) {
        Optional<AdjustedRecipeResponse> adjustedRecipe = recipeService.adjustRecipeServings(id, servings);
        return adjustedRecipe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createWithIngredients")
    public ResponseEntity<Recipe> createRecipeWithIngredients(@RequestBody RecipeWithIngredientsRequest request) {
        Recipe createdRecipe = recipeService.createRecipeWithIngredients(request);
        return ResponseEntity.ok(createdRecipe);
    }
}
