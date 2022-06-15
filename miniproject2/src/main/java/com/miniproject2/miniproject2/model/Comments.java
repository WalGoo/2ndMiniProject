package com.miniproject2.miniproject2.model;

import com.miniproject2.miniproject2.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Comments")
@NoArgsConstructor
@Getter
@Setter
public class Comments {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentsId;

    @Column(nullable = false)
    private String commentsContent;

    @Column(nullable = false)
    private Long recipesId;

    @Column(nullable = false)
    private String username;

    public Comments(CommentDto commentDto) {
        this.commentsContent = commentDto.getCommentsContent();
        this.recipesId = commentDto.getRecipesId();
        this.username = commentDto.getUsername();
    }

    // 댓글 수정
    public void updateComments(CommentDto commentDto) {
        this.commentsContent = commentDto.getCommentsContent();
    }
}
