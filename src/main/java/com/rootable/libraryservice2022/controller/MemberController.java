package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.repository.MemberRepository;
import com.rootable.libraryservice2022.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String joinForm(@ModelAttribute("member") Member member) {

        log.info(">>> Show Join Form");
        return "members/signup";

    }

    @PostMapping("/add")
    public String join(@Valid @ModelAttribute Member form, BindingResult bindingResult) {

        Member member = Member.builder()
                .name(form.getName())
                .loginId(form.getLoginId())
                .password(form.getPassword())
                .email(form.getEmail())
                .role(Role.USER)
                .build();

        duplicationCheckLoginId(member, bindingResult); //중복 아이디 검증
        duplicationCheckEmail(member, bindingResult); //중복 이메일 검증

        if (bindingResult.hasErrors()) {
            log.info("검증 에러 errors={}", bindingResult);
            return "members/signup";
        }

        log.info("정상 입력으로 회원 가입 진행");
        memberService.join(member);
        return "redirect:/";

    }

    private void duplicationCheckLoginId(Member member, BindingResult bindingResult) {

        if (memberRepository.findByLoginId(member.getLoginId()) != null) {
            log.info(">>> 로그인 ID 중복");
            bindingResult.rejectValue("loginId", "overlap");
        }

    }

    private void duplicationCheckEmail(Member member, BindingResult bindingResult) {

        if (memberRepository.findByEmail(member.getEmail()) != null) {
            log.info(">>> 로그인 Email 중복");
            bindingResult.rejectValue("email", "duplication");
        }

    }

}
