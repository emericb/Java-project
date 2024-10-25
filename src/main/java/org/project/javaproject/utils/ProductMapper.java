package org.project.javaproject.utils;

import org.project.javaproject.model.Product;
import org.project.javaproject.service.ProductApiResponse;

public class ProductMapper {

    public static Product mapToProduct(ProductApiResponse apiResponse) {
        Product product = new Product();
        product.setCode(apiResponse.getCode() != null ? apiResponse.getCode() : "N/A");

        if (apiResponse.getProductDetails() != null) {
            product.setName(apiResponse.getProductDetails().getAbbreviatedProductNameFr() != null ? apiResponse.getProductDetails().getAbbreviatedProductNameFr() : "Unknown Product");
            product.setCategory(apiResponse.getProductDetails().getCategories() != null ? apiResponse.getProductDetails().getCategories() : "Uncategorized");
            product.setQuantity(apiResponse.getProductDetails().getQuantity() != null ? Integer.parseInt(apiResponse.getProductDetails().getQuantity()) : 0);
            product.setEnergyKcal(apiResponse.getProductDetails().getNutriments().getEnergyKcal() != 0 ? apiResponse.getProductDetails().getNutriments().getEnergyKcal() : 0);
            product.setAllergens(apiResponse.getProductDetails().getAllergens() != null ? apiResponse.getProductDetails().getAllergens() : "None");
            product.setLabels(apiResponse.getProductDetails().getLabels() != null ? apiResponse.getProductDetails().getLabels() : "No Labels");
        } else {
            product.setName("Unknown Product");
            product.setCategory("Uncategorized");
            product.setQuantity(0);
            product.setEnergyKcal(0);
            product.setAllergens("None");
            product.setLabels("No Labels");
        }

        return product;
    }
}
