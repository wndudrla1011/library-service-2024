package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Comment;
import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import com.rootable.libraryservice2022.repository.CommentRepository;
import com.rootable.libraryservice2022.repository.MemberRepository;
import com.rootable.libraryservice2022.repository.PostsRepository;
import com.rootable.libraryservice2022.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;

    /*
    * 댓글 생성
    * */
    @Transactional
    public Long commentSave(String loginId, Long id, CommentRequestDto dto) {

        Member member = memberRepository.findByLoginId(loginId);
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패 : 해당 게시글이 존재하지 않습니다. id=" + id));

        dto.setMember(member);
        dto.setPosts(posts);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();

    }

    /*
     * 댓글 조회
     * */
    public Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
    }

    /*
     * 댓글 수정
     * */
    @Transactional
    public Long update(Long id, CommentRequestDto dto) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        comment.update(dto.getComment());

        return id;

    }

}
