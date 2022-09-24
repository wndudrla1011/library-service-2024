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
     * 게시글 저장
     * */
    @Transactional
    public Long savePost(Long memberId, Long bookId, PostDto postDto) {

        //엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Book book = bookRepository.findById(bookId).get();

        return postsRepository.save(postDto.toEntity(member, book)).getId();

    }

}
