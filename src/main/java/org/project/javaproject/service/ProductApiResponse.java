package org.project.javaproject.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductApiResponse {
    @JsonProperty("code")
    private String code;

    @JsonProperty("product")
    private ProductDetails productDetails;
}
