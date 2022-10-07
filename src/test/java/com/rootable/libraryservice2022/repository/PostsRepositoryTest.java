package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.*;
import com.rootable.libraryservice2022.service.BookService;
import com.rootable.libraryservice2022.service.MemberService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    BookService bookService;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void register_baseTimeEntity() {

        //given
        LocalDateTime now = LocalDateTime.of(2022, 9, 24, 0, 0, 0);

        Member member = memberService.findByLoginId("admin11");
        Book book = bookService.findByTitle("원씽");

        Posts posts = Posts.builder()
                .title("aaa")
                .content("hello")
                .member(member)
                .book(book)
                .build();

        postsRepository.save(posts);

        //when
        Posts savedPost = postsRepository.findPosts().stream()
                .filter(post -> post.getTitle().equals("aaa"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        System.out.println("post.getCreatedDate() = " + savedPost.getCreatedDate());
        System.out.println("post.getModifiedDate() = " + savedPost.getModifiedDate());

        //then
        assertThat(savedPost.getCreatedDate()).isAfter(now);
        assertThat(savedPost.getModifiedDate()).isAfter(now);

    }

    @Test
    public void findPosts() {

        Member member = memberService.findByLoginId("admin11");
        Book book = bookService.findByTitle("원씽");

        //given
        Posts post1 = Posts.builder()
                .title("aaa")
                .content("hello")
                .member(member)
                .book(book)
                .build();

        Posts post2 = Posts.builder()
                .title("bbb")
                .content("hello")
                .member(member)
                .book(book)
                .build();

        postsRepository.save(post1);
        postsRepository.save(post2);

        //when
        List<Posts> posts = postsRepository.findPosts();
        Posts findPost1 = posts.stream()
                .filter(p -> p.getTitle().equals("aaa"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. title=" + post1.getTitle()));

        Posts findPost2 = posts.stream()
                .filter(p -> p.getTitle().equals("bbb"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. title=" + post2.getTitle()));

        for (Posts post : posts) {
            System.out.println("post.getTitle() = " + post.getTitle());
        }

        //then
        assertThat(posts.contains(findPost1)).isTrue();
        assertThat(posts.contains(findPost2)).isTrue();

    }

    @Test
    public void findMyPosts() {

        Member myOne = memberService.findByLoginId("test1");
        Member another = memberService.findByLoginId("admin11");
        Book book = bookService.findByTitle("원씽");

        //given
        Posts myPost = Posts.builder()
                .title("나의 도서")
                .content("hello")
                .member(myOne)
                .book(book)
                .build();

        Posts anotherPost = Posts.builder()
                .title("관리자 도서")
                .content("hello")
                .member(another)
                .book(book)
                .build();

        postsRepository.save(myPost);
        postsRepository.save(anotherPost);

        //when
        List<Posts> myPosts = postsRepository.findMyPosts(myOne.getId());
        Posts findMyPost = myPosts.stream()
                .filter(my -> my.getTitle().equals("나의 도서"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. title=" + myPost.getTitle()));

        //then
        assertThat(myPosts.contains(findMyPost)).isTrue();
        assertThat(myPosts.stream()
                .filter(my -> my.getTitle().equals("관리자 도서"))
                .findFirst().isEmpty()).isTrue();

    }

}