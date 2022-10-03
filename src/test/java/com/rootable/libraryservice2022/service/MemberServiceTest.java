package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.repository.MemberRepository;
import com.rootable.libraryservice2022.web.dto.MemberDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    /*
     * TestDataInit 빈 컨테이너에서 제외시키고 테스트하기
     * */

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void join() {

        //given
        MemberDto memberDto = MemberDto.builder()
                .name("kim")
                .loginId("test2")
                .password("1111@@gg")
                .email("ss@gmail.com")
                .role(Role.USER)
                .build();

        //when
        Long savedId = memberService.join(memberDto);
        MemberDto savedMemberDto = memberService.getMember(savedId);

        //then
        assertEquals(memberDto, savedMemberDto);

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

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("존재하지 않는 회원 조회")
    public void notFineOne() {

        //given
        Member member = Member.builder()
                .name("kim")
                .loginId("test2")
                .password("1111!!ee")
                .email("oooo@gmail.com")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        //when
        Long failId = 2L;
        memberService.findOne(failId);

        //then
        fail("존재하지 않은 회원 조회로 예외가 발생해야 한다.");

    }

}