package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Status;
import com.rootable.libraryservice2022.repository.BookRepository;
import com.rootable.libraryservice2022.web.dto.BookSaveDto;
import com.rootable.libraryservice2022.web.dto.BookUpdateDto;
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

    @Test
    public void create() {

        //given
        BookSaveDto dto = BookSaveDto.builder()
                .title("book1")
                .writer("a")
                .price(10000)
                .stock(2)
                .status(Status.PERMISSION)
                .build();

        //when
        Long savedId = bookService.create(dto);
        Book book = bookService.findOne(savedId);

        //then
        List<Book> books = bookRepository.findAll();
        assertThat(books.get(0)).usingRecursiveComparison().isEqualTo(book);

    }

    @Test
    public void findOne() {

        //given
        BookSaveDto dto = BookSaveDto.builder()
                .title("book1")
                .writer("a")
                .price(10000)
                .stock(2)
                .status(Status.PERMISSION)
                .build();

        Long savedId = bookService.create(dto);

        //when
        Book savedBook = bookService.findOne(savedId);

        //then
        assertThat(entityManager.contains(savedBook)).isTrue();

    }


    @Test(expected = IllegalArgumentException.class)
    @DisplayName("존재하지 않는 책 조회")
    public void notFindOne() {

        //given
        BookSaveDto dto = BookSaveDto.builder()
                .title("book1")
                .writer("a")
                .price(10000)
                .stock(2)
                .status(Status.PERMISSION)
                .build();

        bookService.create(dto);

        //when
        Long failId = 2L;
        bookService.findOne(failId);

        //then
        fail("존재하지 않는 책 조회로 예외가 발생해야 한다.");

    }

    @Test
    public void update() {

        //given
        Book book1 = Book.builder()
                .title("book1")
                .writer("a")
                .price(10000)
                .stock(2)
                .status(Status.PERMISSION)
                .build();

        BookUpdateDto book2 = createUpdateForm();
        bookService.create(book1);

        //when
        bookService.update(book1.getId(), book2);

        //then
        assertThat(book1.getPrice()).isEqualTo(book2.getPrice());
        assertThat(book1.getStock()).isEqualTo(book2.getStock());
        assertThat(book1.getStatus()).isEqualTo(book2.getStatus());

    }

    private BookUpdateDto createUpdateForm() {
        BookUpdateDto book2 = new BookUpdateDto();
        book2.setPrice(20000);
        book2.setStock(4);
        book2.setStatus(Status.PERMISSION);
        return book2;
    }

    @Test
    public void delete() {

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
        bookService.delete(book.getId());

        //then
        assertThat(entityManager.contains(book)).isFalse();

    }
}