package org.project.javaproject.controller;

import org.project.javaproject.model.Product;
import org.project.javaproject.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<Product> getProductByBarcode(@PathVariable String barcode) {
        Optional<Product> product = productService.getProductByBarcode(barcode);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{str}")
    public Optional<List<Product>> searchProductsByName(@PathVariable String str) {
        return productService.searchProductsByName(str);
    }

    @GetMapping("/category")
    public List<Product> searchProductsByCategory(@RequestParam String category) {
        return productService.searchProductsByCategory(category);
    }

    // Endpoint pour recevoir les produits sélectionnés
    @PostMapping("/add")
    public ResponseEntity<String> addProductsToShoppingList(@RequestBody List<Product> selectedProducts) {
        // Logique pour enregistrer les produits dans la BDD
        productService.saveSelectedProducts(selectedProducts);

        return new ResponseEntity<>("Produits ajoutés avec succès", HttpStatus.OK);
    }

    @PatchMapping("/decrement/{name}")
    public ResponseEntity<Map<String, String>> decrementProductQuantity(@PathVariable String name) {
        try {
            productService.decrementProductQuantityByName(name);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Quantité du produit décrémentée avec succès.");

            return ResponseEntity.ok(response); // Retourne un objet JSON
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
}
