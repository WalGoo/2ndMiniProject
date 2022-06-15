package com.miniproject2.miniproject2.model;

import com.miniproject2.miniproject2.dto.RecipeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name="Recipes")
@NoArgsConstructor
public class Recipes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipesId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String recipesTitle;

    @Column(nullable = false)
    private String recipesContent;

    @Column(nullable = false)
    private String recipesImgUrl;

    public Recipes(RecipeDto recipeDto) {
        this.username = recipeDto.getUsername();
        this.recipesTitle = recipeDto.getRecipesTitle();
        this.recipesContent = recipeDto.getRecipesContent();
        this.recipesImgUrl =  recipeDto.getRecipesImgUrl();
    }

    public void updateRecipe(RecipeDto recipeDto) {
        this.recipesTitle = recipeDto.getRecipesTitle();
        this.recipesContent = recipeDto.getRecipesContent();
        this.recipesImgUrl = recipeDto.getRecipesImgUrl();
    }


}
