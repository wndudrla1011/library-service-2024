package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.*;
import com.rootable.libraryservice2022.repository.BookRepository;
import com.rootable.libraryservice2022.repository.MemberRepository;
import com.rootable.libraryservice2022.repository.PostsRepository;
import com.rootable.libraryservice2022.web.dto.PostDto;
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
    PostsService postsService;

    @Autowired
    EntityManager entityManager;

    @Test
    public void savePost() {

        String title = "First";
        Member member = createMember();
        Book book = createBook();

        //given
        PostDto posts = PostDto.builder()
                .title(title)
                .content("hello")
                .member(member)
                .book(book)
                .build();

        //when
        Long savedPost = postsService.savePost(posts);
        Posts findPost = postsRepository.findById(savedPost).get();

        //then
        assertThat(entityManager.contains(findPost)).isTrue();

        postsRepository.delete(findPost);

    }

    @Test
    public void updatePost() {

        Member member = createMember();
        Book book = createBook();

        //given
        Posts posts = Posts.builder()
                .title("aaa")
                .content("hello")
                .member(member)
                .book(book)
                .build();

        PostDto update = PostDto.builder()
                .title("bbb")
                .content("upgrade")
                .member(member)
                .book(book)
                .build();

        postsRepository.save(posts);

        //when
        Long updatedId = postsService.update(posts.getId(), update);
        Posts updatedPost = postsRepository.findById(updatedId).get();

        //then
        assertThat(updatedPost.getTitle()).isEqualTo("bbb");
        assertThat(updatedPost.getContent()).isEqualTo("upgrade");

    }

    @Test
    public void deletePost() {

        Member member = createMember();
        Book book = createBook();

        //given
        Posts posts = Posts.builder()
                .title("aaa")
                .content("hello")
                .member(member)
                .book(book)
                .build();

        Posts savedPost = postsRepository.save(posts);

        //when
        postsService.delete(savedPost.getId());

        //then
        assertThat(entityManager.contains(savedPost)).isFalse();

    }

    @Test
    @DisplayName("나의 게시물 조회")
    public void myPosts() {

        Member testMember = memberRepository.findByLoginId("test1");
        Book book = createBook();

        //given
        Posts myPosts1 = Posts.builder()
                .title("first")
                .content("hello")
                .member(testMember)
                .book(book)
                .build();

        Posts myPosts2 = Posts.builder()
                .title("second")
                .content("hello")
                .member(testMember)
                .book(book)
                .build();

        Posts another = Posts.builder()
                .title("third")
                .content("hello")
                .member(memberRepository.findByLoginId("staff22"))
                .book(book)
                .build();

        postsRepository.save(myPosts1);
        postsRepository.save(myPosts2);
        postsRepository.save(another);

        //when
        List<Posts> myPosts = postsService.findMyPosts(testMember.getId());

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