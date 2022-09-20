package com.rootable.libraryservice2022.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class BookController {

    @GetMapping("/admin/books")
    public String books(Model model) {
        log.info(">>> Books Management Page");
        return "books/books";
    }

}
