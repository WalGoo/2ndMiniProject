package com.miniproject2.miniproject2.Repository;

import com.miniproject2.miniproject2.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    // 댓글 전체 조회
    List<Comments> findAllByOrderByCommentsIdAsc();

    // 레시피 상세 페이지 댓글 조회
    List<Comments> findByRecipesIdOrderByCommentsIdDesc(Long recipeId);

    // 수정, 삭제용 댓글 조회

    Comments findByCommentsId(Long commentId);

    List<Comments> findAllByOrderByRecipesIdDesc();

}
