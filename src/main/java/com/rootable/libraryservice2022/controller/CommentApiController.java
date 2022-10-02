package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.service.CommentService;
import com.rootable.libraryservice2022.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping(value = "/posts/{postId}/comments/add")
    public ResponseEntity commentSave(@PathVariable Long postId, @RequestBody CommentRequestDto dto,
                                      HttpServletRequest request) {

        log.info("댓글 생성");

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        return ResponseEntity.ok(commentService.commentSave(member.getLoginId(), postId, dto));

    }

    @PutMapping(value = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity update(@PathVariable Long postId, @PathVariable Long commentId,
                                      @RequestBody CommentRequestDto dto) {

        log.info("댓글 수정");

        return ResponseEntity.ok(commentService.update(commentId, dto));

    }

}
