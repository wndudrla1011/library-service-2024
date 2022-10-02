package com.rootable.libraryservice2022.web.dto;

import com.rootable.libraryservice2022.domain.Comment;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponseDto {

    private Long id;

    @NotBlank
    private String comment;
    private Long postsId;
    private String nickname;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.postsId = comment.getPosts().getId();
        this.nickname = comment.getMember().getName();
    }

}
