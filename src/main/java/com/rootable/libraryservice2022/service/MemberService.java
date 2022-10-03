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

    /*
     * 회원 수정
     * */
    @Transactional
    public Long update(Long memberId, Member updateParam) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. id=" + memberId));

        member.update(updateParam.getLoginId(), updateParam.getPassword(), member.getRole());

        return memberId;

    }

    /*
     * 회원 ID 중복 확인
     * */
    public Member checkDuplicatedLoginId(Long id, String loginId) {
        return memberRepository.checkDuplicatedLoginId(id, loginId);
    }

}
