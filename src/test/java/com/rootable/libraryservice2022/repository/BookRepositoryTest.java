package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Status;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @After
    public void cleanUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void findBooks() {

        String title1 = "원씽";
        String title2 = "밤의 문화사";

        //given
        Book book1 = Book.builder()
                .title(title1)
                .writer("게리 켈러")
                .price(12600)
                .stock(5)
                .status(Status.PERMISSION)
                .build();

        Book book2 = Book.builder()
                .title(title2)
                .writer("로저 에커치")
                .price(25000)
                .stock(0)
                .status(Status.DENIED)
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);

        //when
        List<Book> books = bookRepository.findBooks();

        //then
        for (Book book : books) {
            System.out.println("book = " + book.getTitle());
        }

        Book findBook1 = books.stream()
                .filter(b -> b.getTitle().equals(title1))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());

        Book findBook2 = books.stream()
                .filter(b -> b.getTitle().equals(title2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());

        assertThat(findBook1.getTitle()).isEqualTo(title1);
        assertThat(findBook2.getTitle()).isEqualTo(title2);

    }
}
