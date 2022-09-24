package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Book;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
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
    private final BookRepository bookRepository;
    private final PostsRepository postsRepository;

    /*
     * 게시글 저장
     * */
    @Transactional
    public Long savePost(PostDto postDto) {
        return postsRepository.save(postDto.toEntity()).getId();
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

}
