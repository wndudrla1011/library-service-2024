package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    public void findMembers() {

        String name = "kim";
        String loginId = "aaa";

        //given
        Member member1 = Member.builder()
                .name(name)
                .loginId(loginId)
                .password("1111")
                .email("ssss@gmail.com")
                .role(Role.USER)
                .build();

        Member member2 = Member.builder()
                .name("joo")
                .loginId("bbb")
                .password("1111")
                .email("ssss@gmail.com")
                .role(Role.USER)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> members = memberRepository.findMembers();

        //then
        for (Member member : members) {
            System.out.println("member.getName() = " + member.getName());
        }

        Member findMember = members.stream()
                .filter(m -> m.getLoginId().equals("aaa"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());

        assertThat(findMember.getName()).isEqualTo(name);

    }

    @Test
    public void findByLoginIdAndEmail() {

        String loginId1 = "aaa";
        String loginId2 = "bbb";

        String email1 = "gggg@gmail.com";
        String email2 = "ssss@gmail.com";

        //given
        Member member1 = Member.builder()
                .name("kim")
                .loginId(loginId1)
                .password("1111")
                .email(email1)
                .role(Role.USER)
                .build();

        Member member2 = Member.builder()
                .name("joo")
                .loginId(loginId2)
                .password("1111")
                .email(email2)
                .role(Role.USER)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        Member findMember1 = memberRepository.findByLoginId(loginId1);
        Member findMember2 = memberRepository.findByEmail(email1);

        //then
        assertThat(findMember1.getLoginId()).isEqualTo(loginId1);
        assertThat(findMember1.getName()).isEqualTo("kim");

        assertThat(findMember2.getEmail()).isEqualTo(email1);
        assertThat(findMember2.getLoginId()).isEqualTo(loginId1);

    }

    @Test
    public void certify(){

        String loginId = "aaa";
        String pw = "1111";

        //given
        Member member = Member.builder()
                .name("kim")
                .loginId(loginId)
                .password(pw)
                .email("ssss@gmail.com")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        //when
        Member certifiedMember = memberRepository.certify(loginId, pw);
        System.out.println("LoginId = " + certifiedMember.getLoginId());
        System.out.println("Password = " + certifiedMember.getPassword());

        //then
        assertThat(certifiedMember).isNotNull();

    }

    @Test
    public void checkDuplicatedLoginId() {

        Long id = 1L;
        String loginId = "test1";
        String updateId = "test2";
        String password = "1111!!ee";

        //given
        Member member1 = Member.builder()
                .name("kim")
                .loginId(loginId)
                .password(password)
                .email("ssss@gmail.com")
                .role(Role.USER)
                .build();

        Member member2 = Member.builder()
                .name("kim")
                .loginId(updateId)
                .password(password)
                .email("ddss@gmail.com")
                .role(Role.USER)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        member1.update(updateId, password, Role.USER);
        Member member = memberRepository.checkDuplicatedLoginId(id, updateId);

        System.out.println("Updated LoginId = " + member1.getLoginId());
        System.out.println("duplicated LoginId = " + member.getLoginId());

        //then
        assertThat(member.getLoginId()).isEqualTo(updateId);

    }

}