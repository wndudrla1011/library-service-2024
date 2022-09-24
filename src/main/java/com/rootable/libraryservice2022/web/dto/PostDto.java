package com.rootable.libraryservice2022.web.dto;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Member member;
    private Book book;
    private Long fileId;

    public Posts toEntity(Member member, Book book) {
        Posts build = Posts.builder()
                .id(id)
                .title(title)
                .content(content)
                .member(member)
                .book(book)
                .fileId(fileId)
                .build();
        return build;
    }

    @Builder
    public PostDto(Long id, String title, String content, Member member, Book book, Long fileId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
        this.book = book;
        this.fileId = fileId;
    }

}
