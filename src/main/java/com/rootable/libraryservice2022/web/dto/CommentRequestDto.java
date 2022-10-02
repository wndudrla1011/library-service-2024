package com.rootable.libraryservice2022.web.dto;

import com.rootable.libraryservice2022.domain.Comment;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {

    private Long id;

    @NotBlank
    private String comment;

    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private Posts posts;
    private Member member;

    //DTO -> Entity
    public Comment toEntity() {
        Comment build = Comment.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .posts(posts)
                .member(member)
                .build();

        return build;
    }

}
