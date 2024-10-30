package org.project.javaproject.service;

import org.project.javaproject.model.Product;
import org.project.javaproject.repository.ProductRepository;
import org.project.javaproject.utils.ProductMapper;
import org.project.javaproject.utils.SearchProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void saveSelectedProducts(List<Product> selectedProducts) {
        for (Product product : selectedProducts) {
            // Chercher le produit par son nom ou autre identifiant
            Product existingProduct = productRepository.findByName(product.getName())
                    .orElse(new Product());

            // Met à jour les informations du produit (ici, juste la quantité)
            existingProduct.setName(product.getName());
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());

            // Enregistrer ou mettre à jour dans la base de données
            productRepository.save(existingProduct);
        }
    }

    @Transactional
    public void decrementProductQuantityByName(String productName) {
        Product existingProduct = productRepository.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé : " + productName));

        if (existingProduct.getQuantity() > 0) {
            existingProduct.setQuantity(existingProduct.getQuantity() - 1); // Décrémente la quantité
            productRepository.save(existingProduct); // Enregistre les modifications
        } else {
            throw new RuntimeException("La quantité ne peut pas être inférieure à zéro pour le produit : " + productName);
        }
    }
}
