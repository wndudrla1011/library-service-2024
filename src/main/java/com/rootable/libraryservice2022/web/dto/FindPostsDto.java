package com.rootable.libraryservice2022.web.dto;

import com.rootable.libraryservice2022.domain.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindPostsDto {

    private Posts post;
    private String author;

    @Builder
    public FindPostsDto(Posts post, String author) {
        this.post = post;
        this.author = author;
    }
}
