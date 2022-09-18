package com.rootable.libraryservice2022;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    /*
    * 테스트용 데이터 추가
    * */
    @PostConstruct
    public void init() {

        Member member1 = Member.builder()
                .name("test")
                .loginId("test1")
                .password("1111!!aa")
                .email("test@gmail.com")
                .role(Role.USER)
                .build();

        Member member2 = Member.builder()
                .name("bot1")
                .loginId("admin11")
                .password("1111!!bb")
                .email("admin@gmail.com")
                .role(Role.ADMIN)
                .build();

        Member member3 = Member.builder()
                .name("bot2")
                .loginId("staff22")
                .password("1111!!cc")
                .email("staff@gmail.com")
                .role(Role.STAFF)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

    }

}
