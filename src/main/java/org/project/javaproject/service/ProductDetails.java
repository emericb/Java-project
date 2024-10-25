package org.project.javaproject.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetails {
    @JsonProperty("code")
    private String code;

    @JsonProperty("abbreviated_product_name_fr")
    private String abbreviatedProductNameFr;

    @JsonProperty("categories_hierarchy")
    private String[] categories;

    @JsonProperty("product_quantity")
    private String quantity;

    @JsonProperty("nutriments")
    private NutrimentDetails nutriments;

    @JsonProperty("allergens_imported")
    private String allergens;

    @JsonProperty("labels")
    private String labels;

    public String getCategories() {
        for (String cat : this.categories) {
            if (cat.startsWith("fr:")) {
                cat = cat.substring(3);
                return cat;
            }
        }
        return null;
    }
}
