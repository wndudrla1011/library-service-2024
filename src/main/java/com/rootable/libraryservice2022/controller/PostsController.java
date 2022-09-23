package com.rootable.libraryservice2022.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostsController {

    @GetMapping("/posts")
    public String posts() {



        return "posts/posts";

    }

}
