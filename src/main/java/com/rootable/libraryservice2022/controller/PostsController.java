package com.rootable.libraryservice2022.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
    public String write(@RequestParam("bookId") Long bookId,
                        @RequestParam("file") MultipartFile files,
                        PostDto postDto, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        //파일 -> 서버 (저장/업로드)
        try {
            String originFilename = files.getOriginalFilename(); //고객이 업로드한 파일명
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

            postsService.savePost(member.getId(), bookId, postDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/posts";

    }

}
