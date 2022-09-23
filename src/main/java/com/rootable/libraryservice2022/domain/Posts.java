package com.rootable.libraryservice2022.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    @NotBlank
    private Member member; //게시물 작성자

    @OneToOne
    @JoinColumn(name = "book_id")
    @NotBlank
    private Book book; //신청 도서

    @NotNull
    private UploadFile attachFile; //첨부 파일(회원카드)

    private List<UploadFile> imageFiles; //이미지 파일들

}
