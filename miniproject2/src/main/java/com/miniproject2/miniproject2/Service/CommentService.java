package com.miniproject2.miniproject2.Service;

import com.miniproject2.miniproject2.Repository.CommentRepository;
import com.miniproject2.miniproject2.Repository.RecipeRepository;
import com.miniproject2.miniproject2.dto.CommentDto;
import com.miniproject2.miniproject2.model.Comments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;

    public CommentService(CommentRepository commentRepository, RecipeRepository recipeRepository) {
        this.commentRepository = commentRepository;
        this.recipeRepository = recipeRepository;
    }

    // 레시피 상세 페이지 댓글 조회
//    public List<Comments> updateComments(CommentDto commentDto, Long RecipeId) {
//        checkCommentId(commentDto.getCommentId());
//        return commentRepository.findByRecipeIdOrderByCommentIdDesc(commentDto.getCommentId());
//    }

    // 댓글 작성
    @Transactional
    public Long createComment(CommentDto commentDto) {
        checkRecipeId(commentDto.getRecipesId());
        Comments comments = new Comments(commentDto);
        commentRepository.save(comments);
        List<Comments> tempList = commentRepository.findAllByOrderByCommentsIdAsc();
        return tempList.get(tempList.size()-1).getCommentsId();
    }

    // 댓글 수정
    @Transactional
    public void editComment(CommentDto commentDto, Long commentsId) {
//        checkUsername(commentDto.getUsername(), commentDto.getUsername());
        checkCommentId(commentsId);
        Comments comments = commentRepository.findByCommentsId(commentsId);
        comments.updateComments(commentDto);
    }


    // 댓글 삭제
    @Transactional
    public void deleteComment(CommentDto commentDto, Long commentsId) {
//        checkUsername(commentDto.getUsername(), commentDto.getUsername());
        checkCommentId(commentDto.getRecipesId());
        commentRepository.deleteById(commentsId);
    }


    // 레시피 Id 검사
    public void checkRecipeId(Long recipesId){
        if(recipeRepository.findByRecipesId(recipesId) == null) {
            throw new NullPointerException("레시피가 존재하지 않음");
        }
    }

    // 댓글 Id 검사
    public void checkCommentId(Long commentsId) {
        if (commentRepository.findByCommentsId(commentsId) == null) {
            throw new NullPointerException("댓글이 존재하지 않음");
        }
    }

    // 댓글 recipeId별 리스트
    public List<Object> test1(){
        List<Comments> testArray = commentRepository.findAllByOrderByRecipesIdDesc();
        List<Object> tempArray = new ArrayList<>();
        Long tempInt = new Long(1);
        for(int i = 0; i < testArray.size()-1; i++){
            if(testArray.get(i).getRecipesId().equals(tempInt)){
                tempArray.add(testArray.get(i));
            } else {
                tempInt++;
            }
        }

        return tempArray;
    }


//     사용자, 작성자 일치 검사
//        public void checkUsername(@AuthenticationPrincipal userDetail, CommentDto commentDto) {
//        if (!userDetail.getUsername().equals(commentDto.getUsername())) {
//            throw new Exception("해당 작성자가 아님");
//        }
//    }
}
