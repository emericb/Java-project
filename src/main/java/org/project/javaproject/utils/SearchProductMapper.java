package org.project.javaproject.utils;

import org.project.javaproject.model.Product;
import org.project.javaproject.service.SearchProductApiResponse;
import org.project.javaproject.service.SearchProductDetails;

import java.util.ArrayList;
import java.util.List;

public class SearchProductMapper {

    public static List<Product> mapToProduct(SearchProductApiResponse apiResponse) {
        List<SearchProductDetails> detailsList = apiResponse.getProducts();
        if (detailsList == null || detailsList.isEmpty()) {
            return new ArrayList<>();
        }
        List<Product> products = new ArrayList<>();

        for (SearchProductDetails detail : detailsList) {
            Product product = new Product();
            product.setCode(detail.getCode() != null ? detail.getCode() : "N/A");
            product.setName(detail.getAbbreviatedProductNameFr() != null ? detail.getAbbreviatedProductNameFr() : "Unknown Product");
            product.setCategory(detail.getCategories() != null ? detail.getCategories() : "Uncategorized");
            product.setQuantity(detail.getQuantity() != null ? (int) Double.parseDouble(detail.getQuantity()) : 0);
            product.setEnergyKcal(detail.getNutriments().getEnergyKcal() != 0 ? detail.getNutriments().getEnergyKcal() : 0);
            product.setAllergens(detail.getAllergens() != null ? detail.getAllergens() : "None");
            product.setLabels(detail.getLabels() != null ? detail.getLabels() : "No Labels");
            products.add(product);
        }

        return products;
    }
}
