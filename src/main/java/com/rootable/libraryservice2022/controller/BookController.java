package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Status;
import com.rootable.libraryservice2022.service.BookService;
import com.rootable.libraryservice2022.web.dto.BookSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/admin/books/add")
    public String bookForm(Model model) {

        log.info(">>> Show Book Registration Page");

        model.addAttribute("book", new Book());
        model.addAttribute("statusList", Status.values());
        return "books/addBook";

    }

    @PostMapping("/admin/books/add")
    public String create(@Validated @ModelAttribute("book") BookSaveForm form, BindingResult bindingResult) {

        Book book = Book.builder()
                .title(form.getTitle())
                .writer(form.getWriter())
                .price(form.getPrice())
                .stock(form.getStock())
                .status(form.getStatus())
                .build();

        if (bindingResult.hasErrors()) {
            log.info("검증 에러 errors={}", bindingResult);
            return "books/addBook";
        }

        log.info("정상 입력으로 도서 등록 진행");

        bookService.create(book);
        return "redirect:/admin/books"; //등록 확인 페이지로 수정해야 됨

    }

}
