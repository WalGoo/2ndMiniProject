package com.miniproject2.miniproject2.Service;

import com.miniproject2.miniproject2.Repository.CommentRepository;
import com.miniproject2.miniproject2.Repository.RecipeRepository;
import com.miniproject2.miniproject2.dto.CommentDto;
import com.miniproject2.miniproject2.model.Comments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void createComment(CommentDto commentDto) {
        checkRecipeId(commentDto.getRecipeId());
        Comments comments = new Comments(commentDto);
        commentRepository.save(comments);
    }

    // 댓글 수정
    @Transactional
    public void editComment(CommentDto commentDto, Long commentId) {
//        checkUsername(commentDto.getUsername(), commentDto.getUsername());
        checkCommentId(commentId);
        Comments comments = commentRepository.findByCommentId(commentId);
        comments.updateComments(commentDto);
    }


    // 댓글 삭제
    @Transactional
    public void deleteComment(CommentDto commentDto, Long commentId) {
//        checkUsername(commentDto.getUsername(), commentDto.getUsername());
        checkCommentId(commentDto.getRecipeId());
        commentRepository.deleteById(commentId);
    }


    // 레시피 Id 검사
    public void checkRecipeId(Long recipeId){
        if(recipeRepository.findByRecipeId(recipeId) == null) {
            throw new NullPointerException("레시피가 존재하지 않음");
        }
    }

    // 댓글 Id 검사
    public void checkCommentId(Long commentId) {
        if (commentRepository.findByCommentId(commentId) == null) {
            throw new NullPointerException("댓글이 존재하지 않음");
        }
    }


//     사용자, 작성자 일치 검사
//        public void checkUsername(@AuthenticationPrincipal userDetail, CommentDto commentDto) {
//        if (!userDetail.getUsername().equals(commentDto.getUsername())) {
//            throw new Exception("해당 작성자가 아님");
//        }
//    }
}
