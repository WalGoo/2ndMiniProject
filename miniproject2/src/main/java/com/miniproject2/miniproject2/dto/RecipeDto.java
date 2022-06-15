package com.miniproject2.miniproject2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDto {

    private Long recipesId;
    private String username;
    private String recipesContent;
    private String recipesTitle;
    private String recipesImgUrl;

}
