package com.rootable.practiceJwt.service;

import com.rootable.practiceJwt.exception.NotFoundMemberException;
import com.rootable.practiceJwt.repository.MemberRepository;
import com.rootable.practiceJwt.util.SecurityUtil;
import com.rootable.practiceJwt.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    * email -> 내 정보 조회
    * */
    @Transactional
    public MemberDto getMemberWithAuthorities(String email) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    /*
     * SecurityContextHolder -> 내 정보 조회
     * */
    @Transactional
    public MemberDto getMyMemberWithAuthorities() {
        return MemberDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(memberRepository::findOneWithAuthoritiesByEmail)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }

}
