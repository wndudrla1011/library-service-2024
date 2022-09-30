package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.*;
import com.rootable.libraryservice2022.repository.BookRepository;
import com.rootable.libraryservice2022.repository.MemberRepository;
import com.rootable.libraryservice2022.repository.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostsServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    EntityManager entityManager;

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
        assertThat(entityManager.contains(findPost)).isTrue();

    }

    @Test
    @DisplayName("나의 게시물 조회")
    public void myPosts() {

        Member testMember = memberRepository.findByLoginId("test1");

        //given
        Posts myPosts1 = Posts.builder()
                .id(1L)
                .title("first")
                .content("hello")
                .member(testMember)
                .book(bookRepository.findById(1L).get())
                .build();

        Posts myPosts2 = Posts.builder()
                .id(2L)
                .title("second")
                .content("hello")
                .member(testMember)
                .book(bookRepository.findById(2L).get())
                .build();

        Posts another = Posts.builder()
                .id(3L)
                .title("third")
                .content("hello")
                .member(memberRepository.findByLoginId("staff22"))
                .book(bookRepository.findById(2L).get())
                .build();

        postsRepository.save(myPosts1);
        postsRepository.save(myPosts2);
        postsRepository.save(another);

        //when
        List<Posts> myPosts = postsRepository.findMyPosts(testMember.getId());

        //then
        assertThat(myPosts.size()).isEqualTo(2);
        for (Posts myPost : myPosts) {
            System.out.println("myPost.getTitle() = " + myPost.getTitle());
            assertThat(myPost.getMember().getLoginId()).isEqualTo("test1");
        }

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


}