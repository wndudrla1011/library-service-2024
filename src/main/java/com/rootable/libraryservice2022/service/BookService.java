package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /*
     * 도서 등록
     * */
    @Transactional
    public Long create(Book book) {
        return bookRepository.save(book).getId();
    }

    /*
     * 전체 도서 조회
     * */
    public List<Book> books() {
        return bookRepository.findBooks();
    }

}
