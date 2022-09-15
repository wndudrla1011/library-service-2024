package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
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

    @GetMapping("/add")
    public String joinForm(@ModelAttribute("member") Member member) {

        log.info(">>> Show Join Form");
        return "members/signup";

    }

    @PostMapping("/add")
    public String join(@Valid @ModelAttribute Member form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "members/signup";
        }

        Member member = Member.builder()
                .name(form.getName())
                .loginId(form.getLoginId())
                .password(form.getPassword())
                .email(form.getEmail())
                .role(Role.USER)
                .build();

        memberService.join(member);
        return "redirect:/";

    }

}
