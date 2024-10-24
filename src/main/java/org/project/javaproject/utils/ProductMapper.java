package org.project.javaproject.utils;

import org.project.javaproject.model.Product;
import org.project.javaproject.service.ProductApiResponse;

public class ProductMapper {

    public static Product mapToProduct(ProductApiResponse apiResponse) {
        Product product = new Product();
        product.setCode(apiResponse.getCode());
        product.setName(apiResponse.getProductDetails().getAbbreviatedProductNameFr());
        product.setCategory(apiResponse.getProductDetails().getCategories());
        product.setQuantity(apiResponse.getProductDetails().getQuantity());
        product.setEnergyKcal(apiResponse.getProductDetails().getNutriments().getEnergyKcal());
        product.setAllergens(apiResponse.getProductDetails().getAllergens());
        return product;
    }
}
