package com.rootable.libraryservice2024.service;

import com.rootable.libraryservice2024.domain.Authority;
import com.rootable.libraryservice2024.domain.Member;
import com.rootable.libraryservice2024.exception.DuplicateMemberException;
import com.rootable.libraryservice2024.jwt.TokenProvider;
import com.rootable.libraryservice2024.repository.MemberRepository;
import com.rootable.libraryservice2024.web.dto.LoginDto;
import com.rootable.libraryservice2024.web.dto.MemberDto;
import com.rootable.libraryservice2024.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    /*
     * 회원 가입
     * */
    @Transactional
    public MemberDto signup(MemberDto dto) {
        if (memberRepository.findOneWithAuthoritiesByEmail(dto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 회원입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return MemberDto.from(memberRepository.save(member));
    }

    /*
    * 로그인
    * */
    @Transactional
    public TokenDto login(LoginDto dto) {
        //로그인 아이디, 패스워드 -> Authentication Token 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        /*
        * 실제로 검증(패스워드 체크)이 이루어지는 부분
        * authenticate 메서드가 실행될 때 loadByUsername 실행됨
        * */
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //인증 정보를 기반으로 JWT 토큰 생성
        String jwt = tokenProvider.createToken(authentication);

        return new TokenDto(jwt);
    }

}
