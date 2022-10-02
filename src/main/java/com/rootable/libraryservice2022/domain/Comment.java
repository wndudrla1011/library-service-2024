package com.rootable.libraryservice2022.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; //댓글 내용

    @CreatedDate
    private String createdDate;

    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //작성자

    @Builder
    public Comment(Long id, String comment, String createdDate, String modifiedDate, Posts posts, Member member) {
        this.id = id;
        this.comment = comment;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.posts = posts;
        this.member = member;
    }

    /*
     * 연관 관계 메서드
     * */
    public void setPosts(Posts posts) {
        this.posts = posts;
        posts.getComments().add(this);
    }

}
