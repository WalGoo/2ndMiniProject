package com.miniproject2.miniproject2.Controller;

import com.miniproject2.miniproject2.Repository.CommentRepository;
import com.miniproject2.miniproject2.Service.CommentService;
import com.miniproject2.miniproject2.dto.CommentDto;
import com.miniproject2.miniproject2.model.Comments;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    // 댓글 전체 조회
    @GetMapping("/api/comments")
    public List<Comments> getAllOfComments() {
        commentService.test1();
        return commentRepository.findAll();
    }

    // 레시피 상세 페이지 댓글 조회
    @GetMapping("/api/comments/{recipesId}")
    public List<Comments> getComments(@PathVariable Long recipesId) {
        return commentRepository.findByRecipesIdOrderByCommentsIdDesc(recipesId);
    }

    // 댓글 작성
    @PostMapping("/api/comments")
    public Long createComment(@RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    // 댓글 수정
    // controller 에서 사용자, 작성자 대조하는 경우
    @PutMapping("/api/comments/{commentsId}")
    public Long editComment(/*@AuthenticationPrincipal userDetail, */@PathVariable Long commentsId, @RequestBody CommentDto commentDto){
//        checkUsername(userDetail.getUsername(), commentDto.getUsername());
        commentService.editComment(commentDto,commentsId);
        return commentsId;
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{commentsId}")
    public Long deleteComment(@PathVariable Long commentsId, @RequestBody CommentDto commentDto){
        commentService.deleteComment(commentDto, commentsId);
        return commentsId;
    }

    // 작성자 체크
//    public void checkUsername(@AuthenticationPrincipal userDetail, CommentDto commentDto) {
//        if (!userDetail.getUsername().equals(recipeDto.getUsername())) {
//            throw new Exception("해당 작성자가 아님");
//        }
//    }
    //
}
