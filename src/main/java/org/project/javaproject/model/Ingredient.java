package org.project.javaproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String unit;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes;

    public Ingredient() {
    }

    public Ingredient(Long id, String name, String unit, Set<Recipe> recipes) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.recipes = recipes;
    }

}
