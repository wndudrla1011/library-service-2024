package com.rootable.libraryservice2024.service;

import com.rootable.libraryservice2024.domain.Member;
import com.rootable.libraryservice2024.repository.MemberRepository;
import com.rootable.libraryservice2024.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /*
     * 회원 가입
     * */
    @Transactional
    public MemberDto signup(MemberDto dto) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 회원입니다.");
        }

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .authority(dto.getAuthority())
                .build();

        return MemberDto.from(memberRepository.save(member));
    }

}
