package org.project.javaproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Product {
    @Id
    private String code;
    private String name;
    private String category;
    private String quantity;
    private double energyKcal;
    private String allergens;

    @ManyToMany(mappedBy = "products")
    private Set<Recipe> recipes;

    public Product() {
    }
}
