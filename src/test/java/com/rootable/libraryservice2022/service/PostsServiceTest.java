package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.*;
import com.rootable.libraryservice2022.repository.BookRepository;
import com.rootable.libraryservice2022.repository.MemberRepository;
import com.rootable.libraryservice2022.repository.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void savePost() {

        String title = "First";

        //given
        Posts posts = Posts.builder()
                .id(1L)
                .title(title)
                .content("hello")
                .member(createMember())
                .book(createBook())
                .build();

        //when
        postsRepository.save(posts);
        Posts findPost = postsRepository.findById(1L).get();

        //then
        assertThat(findPost.getTitle()).isEqualTo(title);

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

    @Test
    public void findPosts() {
    }
}