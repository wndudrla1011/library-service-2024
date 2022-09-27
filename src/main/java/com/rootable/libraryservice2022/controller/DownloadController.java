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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DownloadController {

    private final FileService fileService;
    private final PostsService postsService;
    private final BookService bookService;

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {

        FileDto fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        fileDto.getOriginFilename() + "\"")
                .body(resource);

    }

    @DeleteMapping("/posts/{postId}/edit")
    public ModelAndView deleteFile(@PathVariable Long postId, HttpServletRequest request) {

        log.info("첨부파일 삭제");

        ModelAndView mav = new ModelAndView("/posts/editPost");

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        File file = null;

        try {
            PostDto postDto = postsService.getPost(postId);
            FileDto fileDto = fileService.getFile(postDto.getFileId());
            file = new File(fileDto.getFilePath());
            if (file.delete()) { //서버로 업로드된 파일 삭제
                log.info("파일 삭제 성공");
            } else {
                log.info("파일 삭제 실패");
            }
            //삭제된 파일명 출력
            String originFileName = fileDto.getOriginFilename();
            log.info("removed file = {}", originFileName);
            //DB File 제거
            fileService.delete(fileDto.getId());
            //DB Posts.fileId 제거
            postDto.setFileId(null);
            //DB 새 Posts 저장
            Long renewPostId = postsService.savePost(member.getId(), postDto);
            //갱신한 Posts 뷰로 전달
            PostDto post = postsService.getPost(renewPostId);
            mav.addObject("posts", post);
            mav.addObject("bookList", bookService.books());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;

    }

}
