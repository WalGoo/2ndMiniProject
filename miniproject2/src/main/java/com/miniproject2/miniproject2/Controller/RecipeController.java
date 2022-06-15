package com.miniproject2.miniproject2.Controller;

import com.miniproject2.miniproject2.Repository.RecipeRepository;
import com.miniproject2.miniproject2.Service.RecipeService;
import com.miniproject2.miniproject2.dto.RecipeDto;
import com.miniproject2.miniproject2.model.Recipes;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// @RequestMapping("/api")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    public RecipeController(RecipeRepository recipeRepository, RecipeService recipeService) {
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }

    // 레시피 전체 조회
    @GetMapping("/api/board")
    public List<Recipes> giveAllOfRecipes() {
        System.out.println(recipeRepository.findAllByOrderByRecipesIdAsc());
        return recipeRepository.findAllByOrderByRecipesIdAsc();
    }

    // 레시피 수정
    @PutMapping("/api/board/{recipesId}")
    public Long editRecipe(@PathVariable Long recipesId, @RequestBody RecipeDto recipeDto) {
        recipeService.editRecipe(recipeDto, recipesId);
        return recipesId;
    }

    // 레시피 삭제
    @DeleteMapping("/api/board/{recipesId}")
    public Long deleteRecipe(@PathVariable Long recipesId) {
        recipeService.deleteRecipe(recipesId);
        return recipesId;
    }

    // 레시피 작성
    @PostMapping("/api/board")
    public Long createRecipe(@RequestBody RecipeDto recipeDto) {
        return recipeService.createRecipe(recipeDto);
    }

    // 레시피 상세 조회
    @GetMapping("/api/board/{recipesId}")
    public Recipes giveRecipe(@PathVariable Long recipesId) {
        return recipeService.selectRecipe(recipesId);
    }

}
