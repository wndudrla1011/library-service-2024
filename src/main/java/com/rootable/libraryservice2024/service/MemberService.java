package com.rootable.libraryservice2024.service;

import com.rootable.libraryservice2024.domain.Member;
import com.rootable.libraryservice2024.repository.MemberRepository;
import com.rootable.libraryservice2024.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
     * 이메일 -> 회원 조회
     * */
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
