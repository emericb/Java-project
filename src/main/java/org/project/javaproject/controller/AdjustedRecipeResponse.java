package org.project.javaproject.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AdjustedRecipeResponse {
    private String recipeName;
    private List<ProductQuantity> products;
    private int desiredServings;

    public AdjustedRecipeResponse(String recipeName, List<ProductQuantity> products, int desiredServings) {
        this.recipeName = recipeName;
        this.products = products;
        this.desiredServings = desiredServings;
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
