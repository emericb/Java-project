package org.project.javaproject.controller;

import org.project.javaproject.model.Ingredient;
import org.project.javaproject.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Optional<Ingredient> ingredient = ingredientService.getIngredientById(id);
        return ingredient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<Ingredient> getIngredientByBarcode(@PathVariable String barcode) {
        Optional<Ingredient> ingredient = ingredientService.getIngredientByBarcode(barcode);
        return ingredient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Ingredient> searchIngredientsByName(@RequestParam String name) {
        return ingredientService.searchIngredientsByName(name);
    }

    @GetMapping("/category")
    public List<Ingredient> searchIngredientsByCategory(@RequestParam String category) {
        return ingredientService.searchIngredientsByCategory(category);
    }
}
