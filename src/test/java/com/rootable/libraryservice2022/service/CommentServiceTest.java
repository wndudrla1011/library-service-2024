package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Comment;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import com.rootable.libraryservice2022.repository.PostsRepository;
import com.rootable.libraryservice2022.web.dto.CommentRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    BookService bookService;

    @Autowired
    EntityManager entityManager;

    @Test
    public void commentSave() {

        //given
        Member member = memberService.findMembers().get(0);
        Book book = bookService.books().get(0);
        String com = "Hello";

        Posts posts = Posts.builder()
                .title("aa")
                .member(member)
                .book(book)
                .build();

        CommentRequestDto dto = CommentRequestDto.builder()
                .comment(com)
                .member(member)
                .posts(posts)
                .build();

        postsRepository.save(posts);

        //when
        Long savedId = commentService.commentSave(member.getLoginId(), posts.getId(), dto);

        //then
        Comment comment = commentService.getComment(savedId);
        assertThat(comment.getComment()).isEqualTo(com);

    }

    @Test
    public void update() {

        Member member = memberService.findMembers().get(0);
        Book book = bookService.books().get(0);
        String com = "comment";
        String newCom = "new";

        //given
        Posts posts = Posts.builder()
                .title("aa")
                .member(member)
                .book(book)
                .build();

        CommentRequestDto dto = CommentRequestDto.builder()
                .comment(com)
                .member(member)
                .posts(posts)
                .build();

        CommentRequestDto updateDto = CommentRequestDto.builder()
                .comment(newCom)
                .member(member)
                .posts(posts)
                .build();

        postsRepository.save(posts);
        Long savedId = commentService.commentSave(member.getLoginId(), posts.getId(), dto);

        //when
        Long updatedId = commentService.update(savedId, updateDto);
        Comment comment = commentService.getComment(updatedId);

        //then
        assertThat(comment.getComment()).isEqualTo(newCom);

    }

    @Test
    public void delete() {

        Member member = memberService.findMembers().get(0);
        Book book = bookService.books().get(0);

        //given
        Posts posts = Posts.builder()
                .title("aa")
                .member(member)
                .book(book)
                .build();

        CommentRequestDto dto = CommentRequestDto.builder()
                .comment("bb")
                .member(member)
                .posts(posts)
                .build();

        postsRepository.save(posts);
        Long savedId = commentService.commentSave(member.getLoginId(), posts.getId(), dto);

        //when
        Comment comment = commentService.getComment(savedId);
        commentService.delete(savedId);

        //then
        assertThat(entityManager.contains(comment)).isFalse();

    }

}