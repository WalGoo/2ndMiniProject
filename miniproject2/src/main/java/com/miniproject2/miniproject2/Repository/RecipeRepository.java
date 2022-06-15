package com.miniproject2.miniproject2.Repository;

import com.miniproject2.miniproject2.model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipes, Long> {

    List<Recipes> findAllByOrderByRecipeIdDesc();

    Recipes findByRecipeId(Long recipeId);
}
