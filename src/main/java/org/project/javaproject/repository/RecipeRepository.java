package org.project.javaproject.repository;

import jakarta.transaction.Transactional;
import org.project.javaproject.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r JOIN r.products p WHERE p.name IN :ingredients")
    List<Recipe> findByIngredients(@Param("ingredients") List<String> ingredients);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO recipe_products (recipes_id, products_code) VALUES (:recipeId, :productCode)", nativeQuery = true)
    void addProductToRecipe(@Param("recipeId") Long recipeId, @Param("productCode") String productCode);
}
