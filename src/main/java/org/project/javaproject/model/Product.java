package org.project.javaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
public class Product {
    @Id
    private String code;
    private String name;
    private String category;
    private int quantity;
    private double energyKcal;
    private String allergens;
    private String labels;

    @Setter
    @ManyToMany(mappedBy = "products")
    private Set<Recipe> recipes;

    public Product() {
    }

    @JsonIgnore
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public Set<String> getAllergens() {
        return Arrays.stream(allergens.split(","))
                .collect(Collectors.toSet());
    }
}
