package com.miniproject2.miniproject2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Long commentsId;
    private String commentsContent;
    private Long recipesId;
    private String username;

}
