package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void join() {

        //given
        Member member = Member.builder()
                .name("kim")
                .loginId("test2")
                .password("1111@@gg")
                .email("ss@gmail.com")
                .role(Role.USER)
                .build();

        //when
        Long savedId = memberService.join(member);
        Member findMember = (Member) memberRepository.findById(savedId)
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertEquals(member, findMember);

    }

    @Test
    public void update() {

        String loginId1 = "test2";
        String loginId2 = "test3";

        //given
        Member member1 = Member.builder()
                .name("kim")
                .loginId(loginId1)
                .password("1111!!ee")
                .email("ssss@gmail.com")
                .role(Role.USER)
                .build();

        Member member2 = Member.builder()
                .name("kim")
                .loginId(loginId2)
                .password("1111!!ee")
                .email("ddss@gmail.com")
                .role(Role.USER)
                .build();

        memberRepository.save(member1);

        //when
        memberService.update(member1.getId(), member2);

        //then
        assertThat(member1.getLoginId()).isEqualTo(loginId2);

    }
}