package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Posts;
import com.rootable.libraryservice2022.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/posts")
    public String posts(Model model) {

        log.info(">>> Post List Page");

        List<Posts> posts = postsService.findPosts();

        model.addAttribute("posts", posts);
        return "posts/posts";

    }

}
