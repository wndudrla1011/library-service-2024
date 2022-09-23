package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Status;
import com.rootable.libraryservice2022.repository.BookRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookServiceTest {

    /*
    * TestDataInit 빈 컨테이너에서 제외시키고 테스트하기
    * */

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    EntityManager entityManager;

    @After
    public void cleanUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void create() {

        //given
        Book book = Book.builder()
                .title("book1")
                .writer("a")
                .price(10000)
                .stock(2)
                .status(Status.PERMISSION)
                .build();

        //when
        bookService.create(book);

        //then
        List<Book> books = bookRepository.findBooks();
        assertThat(books.get(0)).usingRecursiveComparison().isEqualTo(book);

    }

    @Test
    public void findOne() {

        //given
        Book book = Book.builder()
                .title("book1")
                .writer("a")
                .price(10000)
                .stock(2)
                .status(Status.PERMISSION)
                .build();

        Long savedId = bookService.create(book);

        //when
        Book savedBook = bookService.findOne(savedId);

        //then
        assertThat(entityManager.contains(savedBook)).isTrue();

    }


    @Test(expected = IllegalArgumentException.class)
    @DisplayName("저장되지 않은 책 조회")
    public void notFindOne() {

        //given
        Book book = Book.builder()
                .title("book1")
                .writer("a")
                .price(10000)
                .stock(2)
                .status(Status.PERMISSION)
                .build();

        bookService.create(book);

        //when
        Long failId = 2L;
        bookService.findOne(failId);

        //then
        fail("예외가 발생해야 한다.");

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}