package org.project.javaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int servings;

    @ManyToMany
    private Set<Product> products;

    @JsonIgnore
    public Set<Product> getProducts() {
        return products;
    }

    public Recipe() {
    }
}
