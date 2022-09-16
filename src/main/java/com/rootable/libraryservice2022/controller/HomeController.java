package com.rootable.libraryservice2022.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String homeLogin() {

        if () {
            log.info(">>> Move to intro page");
            return "intro";
        }

    }



}
