package org.project.javaproject.controller;

import org.project.javaproject.model.Product;
import org.project.javaproject.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
}
