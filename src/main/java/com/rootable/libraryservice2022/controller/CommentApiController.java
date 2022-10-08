package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.service.CommentService;
import com.rootable.libraryservice2022.web.argumentresolver.Login;
import com.rootable.libraryservice2022.web.dto.CommentRequestDto;
import com.rootable.libraryservice2022.web.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments/add")
    public ResponseEntity commentSave(@PathVariable Long postId, @RequestBody CommentRequestDto dto,
                                      @Login SessionMember loginMember) {

        log.info("댓글 생성");
        return ResponseEntity.ok(commentService.commentSave(loginMember.getLoginId(), postId, dto));

    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity update(@PathVariable Long postId, @PathVariable Long commentId,
                                      @RequestBody CommentRequestDto dto) {

        log.info("댓글 수정");
        return ResponseEntity.ok(commentService.update(commentId, dto));

    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public void delete(@PathVariable Long postId, @PathVariable Long commentId) {
        log.info("댓글 삭제");
        commentService.delete(commentId);
    }

}
