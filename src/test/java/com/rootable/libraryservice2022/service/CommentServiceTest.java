package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Comment;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import com.rootable.libraryservice2022.repository.PostsRepository;
import com.rootable.libraryservice2022.web.dto.CommentRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void commentSave() {

        //given
        Member member = memberService.findMembers().get(0);
        Book book = bookService.books().get(0);
        String com = "Hello";

        Posts posts = Posts.builder()
                .id(1L)
                .title("aa")
                .member(member)
                .book(book)
                .build();

        CommentRequestDto dto = CommentRequestDto.builder()
                .id(1L)
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
    }

    @Test
    public void delete() {
    }

}