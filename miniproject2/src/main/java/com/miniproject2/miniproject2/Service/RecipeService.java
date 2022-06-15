package com.miniproject2.miniproject2.Service;

import com.miniproject2.miniproject2.Repository.RecipeRepository;
import com.miniproject2.miniproject2.dto.RecipeDto;
import com.miniproject2.miniproject2.model.Recipes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // 레시피 Create
    @Transactional
    public Long createRecipe(RecipeDto recipeDto) {
        Recipes recipes = new Recipes(recipeDto);
        recipeRepository.save(recipes);
        // 어센딩으로 PK 찾기
        List<Recipes> tempList = recipeRepository.findAllByOrderByRecipesIdAsc();
        return tempList.get(tempList.size()-1).getRecipesId();
    }

    // 레시피 SELECT recipeId FROM Recipes WHERE ..
    @Transactional
    public Recipes selectRecipe(Long recipesId) {
        checkRecipeId(recipesId);
        return recipeRepository.findByRecipesId(recipesId);
    }

    // 레시피 Update
    @Transactional
    public void editRecipe(RecipeDto recipeDto, Long recipesId) {
        checkRecipeId(recipesId);
//      checkUsername(recipeDto.getUsername());
        Recipes recipes = recipeRepository.findByRecipesId(recipesId);
        editRecipeVal(recipeDto);
        recipes.updateRecipe(recipeDto);
    }

    // 레시피 Delete
    @Transactional
    public void deleteRecipe(Long recipeId) {
        checkRecipeId(recipeId);
//      checkUsername(recipeDto.getUsername());
        recipeRepository.deleteById(recipeId);
    }


    // 레시피 ID 체크
    public void checkRecipeId(Long recipesId) {
        if (recipeRepository.findByRecipesId(recipesId) == null) {
            throw new NullPointerException("RecipeId isn't exist.");
        }
    }

    // 레시피 수정 시 빈 칸은 수정 없는 칸으로
    public void editRecipeVal(RecipeDto recipeDto){
        if(recipeDto.getRecipesTitle().isEmpty()){
            recipeDto.setRecipesTitle(recipeRepository.findByRecipesId(recipeDto.getRecipesId()).getRecipesTitle());
        }
        if(recipeDto.getRecipesImgUrl().isEmpty()){
            recipeDto.setRecipesImgUrl(recipeRepository.findByRecipesId(recipeDto.getRecipesId()).getRecipesImgUrl());
        }
        if(recipeDto.getRecipesContent().isEmpty()){
            recipeDto.setRecipesContent(recipeRepository.findByRecipesId(recipeDto.getRecipesId()).getRecipesContent());
        }
    }

    // 작성자 체크
//    public void checkUsername(@AuthenticationPrincipal userDetail) {
//        if (!userDetail.getUsername().equals(recipeDto.getUsername())) {
//            throw new Exception("해당 작성자가 아님");
//        }
//    }
}
