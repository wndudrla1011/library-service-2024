package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.TestDataInit;
import com.rootable.libraryservice2022.domain.*;
import com.rootable.libraryservice2022.web.dto.PostDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void register_baseTimeEntity() {

        //given
        LocalDateTime now = LocalDateTime.of(2022, 9, 24, 0, 0, 0);

        Member member = createMember();
        Book book = createBook();

        Posts posts = Posts.builder()
                .title("aaa")
                .content("hello")
                .member(member)
                .book(book)
                .build();

        postsRepository.save(posts);

        //when
        Posts savedPost = postsRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        System.out.println("post.getCreatedDate() = " + savedPost.getCreatedDate());
        System.out.println("post.getModifiedDate() = " + savedPost.getModifiedDate());

        //then
        assertThat(savedPost.getCreatedDate()).isAfter(now);
        assertThat(savedPost.getModifiedDate()).isAfter(now);

    }

    private Book createBook() {
        Book book = Book.builder()
                .title("원띵")
                .writer("게리 켈러")
                .price(12000)
                .stock(5)
                .status(Status.PERMISSION)
                .build();

        bookRepository.save(book);
        return book;
    }

    private Member createMember() {
        Member member = Member.builder()
                .name("best")
                .loginId("best1")
                .password("1111!!aa")
                .email("best@gmail.com")
                .role(Role.USER)
                .build();

        memberRepository.save(member);
        return member;
    }

}