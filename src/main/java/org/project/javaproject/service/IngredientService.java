package org.project.javaproject.service;

import org.project.javaproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class IngredientService {

    private final ProductService productService;

    @Autowired
    public IngredientService(ProductService productService) {
        this.productService = productService;
    }

    public Set<Product> addIngredients(Set<String> barcodes) {
        Set<Product> products = new HashSet<>();
        for (String barcode : barcodes) {
            productService.getProductByBarcode(barcode).ifPresent(products::add);
        }
        return products;
    }
}
