package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/admin/books")
    public String books(Model model) {

        log.info(">>> Books Management Page");

        List<Book> books = bookService.books();
        model.addAttribute("books", books);

        return "books/books";

    }

}
