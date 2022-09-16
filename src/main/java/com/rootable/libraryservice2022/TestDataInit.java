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

        Member member = Member.builder()
                .name("test")
                .loginId("testId")
                .password("1111")
                .email("test@gmail.com")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

    }

}
