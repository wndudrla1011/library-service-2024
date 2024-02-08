package com.rootable.libraryservice2024.service;

import com.rootable.libraryservice2024.domain.Member;
import com.rootable.libraryservice2024.repository.MemberRepository;
import com.rootable.libraryservice2024.web.dto.MemberDto;
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
    public Long join(MemberDto dto) {
        Boolean isExist = memberRepository.existByUsername(dto.getUsername());

        if (isExist) return 0L; //이미 가입된 username 존재

        return memberRepository.save(dto.toEntity()).getId();
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
    * 회원 DTO 조회
    * */
    @Transactional
    public MemberDto getMember(Long memberId) {

        Member member = memberRepository.findById(memberId).get();

        MemberDto memberDto = MemberDto.builder()
                .nickname(member.getNickname())
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .authority(member.getAuthority())
                .build();

        return memberDto;

    }

    /*
     * 회원 수정
     * */
    @Transactional
    public Long update(Long memberId, MemberDto updateParam) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. id=" + memberId));

        member.update(updateParam.getUsername(), updateParam.getPassword(), updateParam.getAuthority());

        return memberId;

    }

    /*
     * 회원 삭제
     * */
    public void delete(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. id=" + memberId));

        memberRepository.delete(member);

    }

    /*
     * 회원 ID 중복 확인
     * */
    public Member checkDuplicatedLoginId(Long id, String loginId) {
        return memberRepository.checkDuplicatedLoginId(id, loginId);
    }

    /*
     * 로그인 ID -> 회원 조회
     * */
    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    /*
     * 이메일 -> 회원 조회
     * */
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
