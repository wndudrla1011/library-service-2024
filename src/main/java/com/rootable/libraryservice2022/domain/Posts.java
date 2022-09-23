package com.rootable.libraryservice2022.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String content;

    @OneToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member; //게시물 작성자

    @OneToOne
    @JoinColumn(name = "book_id")
    @NotNull
    private Book book; //신청 도서

//    @NotNull
//    private UploadFile attachFile; //첨부 파일(회원카드)

//    private List<UploadFile> imageFiles; //이미지 파일들

    /*
     * 연관관계 메서드
     * */


    /*
    * 생성 메서드
    * */
    public static Posts createPost(String title, String content, Member member,
                                   Book book) {

        Posts posts = new Posts();
        posts.setTitle(title);
        posts.setContent(content);
        posts.setMember(member);
        posts.setBook(book);
//        posts.setAttachFile(attachFile);
//        posts.setImageFiles(imageFiles);

        return posts;

    }

}
