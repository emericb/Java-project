package org.project.javaproject.service;

import org.project.javaproject.model.Product;
import org.project.javaproject.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private final String apiUrl = "https://world.openfoodfacts.org/api/v2/product/";

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Product> getProductByBarcode(String barcode) {
        String url = apiUrl + barcode + ".json";
        ProductApiResponse apiResponse = restTemplate.getForObject(url, ProductApiResponse.class);

        if (apiResponse != null) {
            Product product = ProductMapper.mapToProduct(apiResponse);
            return Optional.of(product);
        }

        return Optional.empty();
    }

    public List<Product> getAllProducts() {
        return List.of();
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.empty();
    }

    public List<Product> searchProductsByName(String name) {
        return List.of();
    }

    public List<Product> searchProductsByCategory(String category) {
        return List.of();
    }
}
