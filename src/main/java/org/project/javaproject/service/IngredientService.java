package org.project.javaproject.service;

import org.project.javaproject.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final RestTemplate restTemplate;
    private final String apiUrl = "https://world.openfoodfacts.org/api/v0/product/";

    @Autowired
    public IngredientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Ingredient> getAllIngredients() {
        // Implémentation pour récupérer tous les ingrédients
        return List.of(); // Placeholder
    }

    public Optional<Ingredient> getIngredientById(Long id) {
        // Implémentation pour récupérer un ingrédient par ID
        return Optional.empty(); // Placeholder
    }

    public Optional<Ingredient> getIngredientByBarcode(String barcode) {
        String url = apiUrl + barcode + ".json";
        Ingredient ingredient = restTemplate.getForObject(url, Ingredient.class);
        return Optional.ofNullable(ingredient);
    }

    public List<Ingredient> searchIngredientsByName(String name) {
        // Implémentation pour rechercher des ingrédients par nom
        return List.of(); // Placeholder
    }

    public List<Ingredient> searchIngredientsByCategory(String category) {
        // Implémentation pour rechercher des ingrédients par catégorie
        return List.of(); // Placeholder
    }
}
