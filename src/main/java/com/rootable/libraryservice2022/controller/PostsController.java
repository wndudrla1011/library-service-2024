package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import com.rootable.libraryservice2022.service.BookService;
import com.rootable.libraryservice2022.service.FileService;
import com.rootable.libraryservice2022.service.PostsService;
import com.rootable.libraryservice2022.web.dto.FileDto;
import com.rootable.libraryservice2022.web.dto.PostDto;
import com.rootable.libraryservice2022.web.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final BookService bookService;
    private final FileService fileService;
    private final FileStore fileStore;

    @GetMapping("/posts")
    public String posts(Model model) {

        log.info(">>> Post List Page");

        List<Posts> posts = postsService.findPosts();

        model.addAttribute("posts", posts);
        return "posts/posts";

    }

    @GetMapping("/posts/add")
    public String addForm(Model model) {

        log.info(">>> Show Post Registration Page");

        model.addAttribute("posts", new Posts());
        model.addAttribute("bookList", bookService.books());
        return "posts/addPost";

    }

    @PostMapping("/posts/add")
    public String write(@Validated @ModelAttribute("posts") PostDto postDto, BindingResult bindingResult,
                        @RequestParam(value = "bookId", required = false) Long bookId,
                        @RequestParam("file") MultipartFile files,
                        Model model, HttpServletRequest request) {

        log.info("게시글 등록");

        model.addAttribute("bookList", bookService.books());

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        postDto.setMember(member);

        //파일 -> 서버 (저장/업로드)
        try {
            String originFilename = files.getOriginalFilename(); //고객이 업로드한 파일명
            if ("".equals(originFilename)) {
                throw new IOException();
            }
            String storeFileName = fileStore.createStoreFileName(originFilename); //서버 저장 파일명
            String saveDir = fileStore.getFileDir();
            if (!new File(saveDir).exists()) {
                try {
                    new File(saveDir).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = fileStore.getFullPath(originFilename);
            files.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOriginFilename(originFilename);
            fileDto.setFilename(storeFileName);
            fileDto.setFilePath(filePath);

            Long fileId = fileService.saveFile(fileDto);
            postDto.setFileId(fileId);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bookId != null) {
                postDto.setBook(bookService.findOne(bookId));
            }

            if (bindingResult.hasFieldErrors("title")) {
                log.info("검증 에러 errors={}", bindingResult);
                return "posts/addPost";
            }

            if (postDto.getBook() == null) {
                log.info("검증 에러 errors={}", bindingResult);
                return "posts/addPost";
            }

            postsService.savePost(member.getId(), postDto);
        }

        return "redirect:/posts";

    }

    @GetMapping("/posts/{postId}")
    public String post(@PathVariable Long postId, Model model) {

        log.info("게시글 정보");

        PostDto post = postsService.getPost(postId);
        model.addAttribute("post", post);

        return "/posts/postInfo";

    }

}
