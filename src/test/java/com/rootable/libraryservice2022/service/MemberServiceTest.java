package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public void findMembers() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void update() {
    }
}