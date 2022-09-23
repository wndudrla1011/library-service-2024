package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.TestDataInit;
import com.rootable.libraryservice2022.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void register_baseTimeEntity() {

        //given
        LocalDateTime now = LocalDateTime.of(2022, 9, 23, 0, 0, 0);

        //when


        //then

    }

    private Book createBook() {
        Book book = Book.builder()
                .title("원띵")
                .writer("게리 켈러")
                .price(12000)
                .stock(5)
                .status(Status.PERMISSION)
                .build();
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

        return member;
    }

}