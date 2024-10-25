package org.project.javaproject.service;

import org.project.javaproject.model.Product;
import org.project.javaproject.utils.ProductMapper;
import org.project.javaproject.utils.SearchProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Product> getProductByBarcode(String barcode) {
        String apiUrl = "https://world.openfoodfacts.org/api/v2/product/";
        String url = apiUrl + barcode + ".json";
        ProductApiResponse apiResponse = restTemplate.getForObject(url, ProductApiResponse.class);

        if (apiResponse != null) {
            Product product = ProductMapper.mapToProduct(apiResponse);
            return Optional.of(product);
        }

        return Optional.empty();
    }


    public Optional<List<Product>> searchProductsByName(String str) {
        String apiUrl = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=";
        String url = apiUrl + str + "&search_simple=1&action=process&json=1";
        SearchProductApiResponse apiResponse = restTemplate.getForObject(url, SearchProductApiResponse.class);
        if (apiResponse != null) {
            List<Product> products = SearchProductMapper.mapToProduct(apiResponse);
            return Optional.of(products);
        }
        return Optional.empty();
    }

    public List<Product> searchProductsByCategory(String category) {
        return List.of();
    }
}
