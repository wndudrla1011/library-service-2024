package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    * 회원 가입
    * */
    @Transactional
    public Long join(Member member) {
        return memberRepository.save(member).getId();
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findMembers();
    }

    /*
    * 회원 조회
    * */
    public Member findOne(Long id) {

        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. id=" + id));

    }

}
