package com.rootable.libraryservice2024.service;

import com.rootable.libraryservice2024.domain.Member;
import com.rootable.libraryservice2024.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /*
     * 로그인 검증
     * */
    public Member validationLogin(String loginId, String password) {
        return memberRepository.certify(loginId, password);
    }

}
