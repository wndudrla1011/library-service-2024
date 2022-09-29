package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Posts;
import com.rootable.libraryservice2022.service.BookService;
import com.rootable.libraryservice2022.service.FileService;
import com.rootable.libraryservice2022.service.PostsService;
import com.rootable.libraryservice2022.web.dto.FileDto;
import com.rootable.libraryservice2022.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final BookService bookService;
    private final FileService fileService;

    @GetMapping("/posts")
    public String posts(Model model) {

        log.info("게시글 관리 페이지");

        List<Posts> posts = postsService.findPosts();

        model.addAttribute("posts", posts);
        return "posts/posts";

    }

    @GetMapping("/posts/add")
    public String addForm(Model model) {

        log.info("게시글 등록 폼 이동");

        List<Book> books = bookService.books();

        model.addAttribute("posts", new Posts());
        model.addAttribute("bookList", books);
        return "posts/addPost";

    }

    @GetMapping("/posts/{postId}")
    public String post(@PathVariable Long postId, Model model) {

        log.info("게시글 정보");

        PostDto post = postsService.getPost(postId);
        model.addAttribute("post", post);

        if (post.getFileId() != null) {
            FileDto file = fileService.getFile(post.getFileId());
            model.addAttribute("filename", file.getOriginFilename());
        }

        return "/posts/postInfo";

    }

    @GetMapping("/posts/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {

        log.info("게시글 수정 폼 이동");

        PostDto post = postsService.getPost(postId);

        if (post.getFileId() != null) {
            FileDto file = fileService.getFile(post.getFileId());
            model.addAttribute("filename", file.getOriginFilename());
        }

        model.addAttribute("posts", post);
        model.addAttribute("bookList", bookService.books());

        return "posts/editPost";

    }

}
