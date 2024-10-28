package org.project.javaproject.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class AdjustedRecipeResponse {
    private String recipeName;
    private List<ProductQuantity> products;
    private int desiredServings;
    private int caloriesPerServing;
    private Set<String> allergens;

    public AdjustedRecipeResponse(String recipeName, List<ProductQuantity> products, int desiredServings, int caloriesPerServing, Set<String> allergens) {
        this.recipeName = recipeName;
        this.products = products;
        this.desiredServings = desiredServings;
        this.caloriesPerServing = caloriesPerServing;
        this.allergens = allergens;
    }

    @Setter
    @Getter
    public static class ProductQuantity {
        private String productName;
        private int quantity;

        public ProductQuantity(String productName, int quantity) {
            this.productName = productName;
            this.quantity = quantity;
        }
    }
}
