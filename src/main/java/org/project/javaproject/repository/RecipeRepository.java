package org.project.javaproject.repository;

import org.project.javaproject.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r JOIN r.products p WHERE p.name IN :ingredients")
    List<Recipe> findByIngredients(@Param("ingredients") List<String> ingredients);
}
