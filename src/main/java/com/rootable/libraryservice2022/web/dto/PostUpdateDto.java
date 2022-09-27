package com.rootable.libraryservice2022.web.dto;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PostUpdateDto {

    private Long id;

    @NotBlank(message = "제목은 필수 입력 값입니다")
    @Size(max = 20, message = "제목은 20자를 넘을 수 없습니다")
    private String title;

    private String content;

    @NotNull(message = "도서를 선택해주세요")
    private Book book;

    private Long fileId;

    @Builder
    public PostUpdateDto(Long id, String title, String content, Book book) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.book = book;
    }

}
