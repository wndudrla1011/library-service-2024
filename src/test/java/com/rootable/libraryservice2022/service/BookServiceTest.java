package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Status;
import com.rootable.libraryservice2022.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

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
    public void books() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}