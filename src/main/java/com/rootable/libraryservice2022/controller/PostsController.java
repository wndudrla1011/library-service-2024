package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.*;
import com.rootable.libraryservice2022.service.BookService;
import com.rootable.libraryservice2022.service.CommentService;
import com.rootable.libraryservice2022.service.FileService;
import com.rootable.libraryservice2022.service.PostsService;
import com.rootable.libraryservice2022.web.MySecured;
import com.rootable.libraryservice2022.web.dto.FileDto;
import com.rootable.libraryservice2022.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final BookService bookService;
    private final FileService fileService;
    private final CommentService commentService;

    public static String SAME_PERSON_KEY = "same";
    public static String SAME_WRITER_KEY = "writer";

    @GetMapping("/posts")
    public String posts(Model model, HttpServletRequest request) {

        log.info("게시글 관리 페이지");

        List<Posts> posts = postsService.findPosts();

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        model.addAttribute("posts", posts);
        model.addAttribute("member", member);
        return "posts/posts";

    }

    @MySecured(role = Role.GUEST)
    @GetMapping("/posts/add")
    public String addForm(Model model) {

        log.info("게시글 등록 폼 이동");

        List<Book> books = bookService.books();

        model.addAttribute("posts", new Posts());
        model.addAttribute("bookList", books);
        return "posts/addPost";

    }

    @GetMapping("/posts/{postId}")
    public String post(@PathVariable Long postId, Model model, HttpServletRequest request) {

        log.info("게시글 정보");

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        Posts post = postsService.findById(postId);
        model.addAttribute("post", post);

        if (post.getFileId() != null) {
            FileDto file = fileService.getFile(post.getFileId());
            model.addAttribute("filename", file.getOriginFilename());
        }

        if (post.getMember().getId().equals(member.getId()) || member.getRole() == Role.ADMIN) {
            model.addAttribute("same", SAME_PERSON_KEY);
        }

        //댓글 리스트
        List<Comment> comments = post.getComments();

        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        //댓글 렌더링
        model.addAttribute("comment", new Comment());


        return "/posts/postInfo";

    }

    @MySecured(role = Role.GUEST)
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

    @GetMapping("/posts/mine")
    public String myList(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        List<Posts> myPosts = postsService.findMyPosts(member.getId());
        model.addAttribute("myPosts", myPosts);

        return "posts/myPosts";

    }

    @GetMapping("/alert/{postId}")
    public String alert(@PathVariable Long postId, Model model) {

        log.info("처리 결과 alert");

        Posts post = postsService.findById(postId);
        String reason = post.getResult().getReason();

        model.addAttribute("msg", reason);
        model.addAttribute("url", "/posts");
        return "posts/alert";

    }

    @GetMapping("/posts/{postId}/comments/add")
    public String commentForm(@PathVariable Long postId, Model model) {

        log.info("댓글 입력 폼 이동");

        model.addAttribute("postId", postId);
        return "comments/commentForm";
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public String editComment(@PathVariable Long postId, @PathVariable Long commentId, Model model,
                              HttpServletRequest request) {

        log.info("댓글 수정");

        Comment comment = commentService.getComment(commentId);
        model.addAttribute("commentId", comment.getId());

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        //댓글 작성자 확인
        if (member.getId().equals(comment.getMember().getId())) {
            model.addAttribute("isWriter", SAME_WRITER_KEY);
        }

        return "comments/editComment";

    }

}
