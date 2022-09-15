package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    public void findByLoginId() {

        String loginId1 = "aaa";
        String loginId2 = "bbb";

        //given
        Member member1 = Member.builder()
                .name("kim")
                .loginId(loginId1)
                .password("1111")
                .email("ssss@gmail.com")
                .role(Role.USER)
                .build();

        Member member2 = Member.builder()
                .name("joo")
                .loginId(loginId2)
                .password("1111")
                .email("ssss@gmail.com")
                .role(Role.USER)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        Member findMember = memberRepository.findByLoginId(loginId1);

        //then
        assertThat(findMember.getLoginId()).isEqualTo(loginId1);
        assertThat(findMember.getName()).isEqualTo("kim");

    }
}