package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.*;
import com.rootable.libraryservice2022.repository.BookRepository;
import com.rootable.libraryservice2022.repository.MemberRepository;
import com.rootable.libraryservice2022.repository.PostsRepository;
import com.rootable.libraryservice2022.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final MemberRepository memberRepository;
    private final PostsRepository postsRepository;

    /*
     * 게시글 저장
     * */
    @Transactional
    public Long savePost(PostDto postDto) {

        Member member = memberRepository.findById(postDto.getMember().getId()).get();
        Posts posts = postDto.toEntity();

        Status status = posts.getBook().getStatus();
        Integer stock = posts.getBook().getStock();

        posts.setMember(member);

        if (status != Status.DENIED && stock > 0) { //재고가 있고 절판 도서가 아닌 경우
            posts.setResult(Result.SUCCESS);
        } else if (status == Status.PERMISSION && stock == 0){ //재고가 없는 경우
            posts.setResult(Result.FAIL);
        } else if (status == Status.DENIED) { //절판 도서인 경우
            posts.setResult(Result.DENIED);
        }

        return postsRepository.save(posts).getId();

    }

    /*
    * 전체 게시글 조회
    * */
    public List<Posts> findPosts() {
        return postsRepository.findPosts();
    }

    /*
     * 게시글 조회
     * */
    @Transactional
    public PostDto getPost(Long id) {

        Posts posts = postsRepository.findById(id).get();

        PostDto postDto = PostDto.builder()
                .id(id)
                .title(posts.getTitle())
                .content(posts.getContent())
                .member(posts.getMember())
                .book(posts.getBook())
                .fileId(posts.getFileId())
                .build();

        return postDto;

    }

    /*
     * 게시글 수정
     * */
    @Transactional
    public Long update(Long id, PostDto requestDto) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getBook());

        return id;

    }

    /*
     * 게시글 삭제
     * */
    @Transactional
    public void delete(Long postId) {

        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));

        postsRepository.delete(posts);

    }

    /*
     * 게시글 파일 수정
     * */
    @Transactional
    public Posts updateFile(Long id, PostDto postDto) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        posts.setFile(postDto.getFileId());

        return posts;

    }

    /*
     * 나의 게시글 조회
     * */
    public List<Posts> findMyPosts(Long memberId) {
        List<Posts> myPosts = postsRepository.findMyPosts(memberId);
        return myPosts;
    }

}
