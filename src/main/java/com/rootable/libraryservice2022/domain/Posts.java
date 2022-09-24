package com.rootable.libraryservice2022.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @NotBlank
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member; //게시물 작성자

    @OneToOne
    @JoinColumn(name = "book_id")
    @NotNull
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
