package com.miniproject2.miniproject2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDto {

    private Long recipeId;
    private String username;
    private String recipeContent;
    private String recipeTitle;
    private String recipeImgUrl;

}
