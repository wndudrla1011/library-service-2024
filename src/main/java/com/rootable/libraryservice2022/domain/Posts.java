package com.rootable.libraryservice2022.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; //게시물 작성자

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; //신청 도서

    private Long fileId;

    @Builder
    public Posts(Long id, String title, String content, Member member, Book book, Long fileId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
        this.book = book;
        this.fileId = fileId;
    }

}
