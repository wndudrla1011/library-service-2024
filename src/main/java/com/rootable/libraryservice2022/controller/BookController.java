package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.domain.Status;
import com.rootable.libraryservice2022.service.BookService;
import com.rootable.libraryservice2022.web.MySecured;
import com.rootable.libraryservice2022.web.dto.BookSaveForm;
import com.rootable.libraryservice2022.web.dto.BookUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @MySecured(role = Role.ADMIN)
    @GetMapping("/admin/books")
    public String books(Model model) {

        log.info(">>> Books Management Page");

        List<Book> books = bookService.books();
        model.addAttribute("books", books);

        return "books/books";

    }

    @MySecured(role = Role.ADMIN)
    @GetMapping("/admin/books/add")
    public String bookForm(Model model) {

        log.info(">>> Show Book Registration Page");

        model.addAttribute("book", new Book());
        model.addAttribute("statusList", Status.values());
        return "books/addBook";

    }

    @MySecured(role = Role.ADMIN)
    @PostMapping("/admin/books/add")
    public String create(@Validated @ModelAttribute("book") BookSaveForm form, BindingResult bindingResult, Model model) {

        model.addAttribute("statusList", Status.values());

        if (form.getStock() != null && form.getStatus() != null) {
            if (form.getStock() > 0 && form.getStatus() == Status.DENIED) {
                bindingResult.reject("invalid");
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("검증 에러 errors={}", bindingResult);
            return "books/addBook";
        }

        log.info("정상 입력으로 도서 등록 진행");

        Book book = Book.builder()
                .title(form.getTitle())
                .writer(form.getWriter())
                .price(form.getPrice())
                .stock(form.getStock())
                .status(form.getStatus())
                .build();

        bookService.create(book);

        return "redirect:/admin/books"; //등록 확인 페이지로 수정해야 됨

    }

    @MySecured(role = Role.ADMIN)
    @GetMapping("/admin/books/{bookId}")
    public String book(@PathVariable Long bookId, Model model) {

        log.info("도서 정보");

        Book findBook = bookService.findOne(bookId);

        model.addAttribute("book", findBook);
        return "books/bookInfo";

    }

    @MySecured(role = Role.ADMIN)
    @GetMapping("/admin/books/{bookId}/edit")
    public String editForm(@PathVariable Long bookId, Model model) {

        log.info(">>> Show Update Book Form");

        Book book = bookService.findOne(bookId);

        model.addAttribute("book", book);
        model.addAttribute("statusList", Status.values());
        return "books/editBook";

    }

    @MySecured(role = Role.ADMIN)
    @PostMapping("/admin/books/{bookId}/edit")
    public String edit(@PathVariable Long bookId, @Validated @ModelAttribute("book") BookUpdateForm form,
                       BindingResult bindingResult, Model model) {

        model.addAttribute("statusList", Status.values());

        if (form.getStock() != null && form.getStatus() != null) {
            if (form.getStock() > 0 && form.getStatus() == Status.DENIED) {
                bindingResult.reject("invalid");
            }
        }

        if (form.getStatus() == null) {
            bindingResult.reject("statusNull");
        }

        if (bindingResult.hasErrors()) {
            log.info("검증 에러 errors={}", bindingResult);
            return "books/editBook";
        }

        log.info("도서 정보 수정 진행");

        bookService.update(bookId, form);
        return "redirect:/admin/books";

    }

}
