package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.repository.MemberRepository;
import com.rootable.libraryservice2022.service.MemberService;
import com.rootable.libraryservice2022.web.MySecured;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/members/add")
    public String joinForm(@ModelAttribute("member") Member member) {

        log.info(">>> Show Join Form");
        return "members/signup";

    }

    @PostMapping("/members/add")
    public String join(@Valid @ModelAttribute("member") Member form, BindingResult bindingResult) {

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

    @MySecured(role = Role.STAFF)
    @GetMapping("/admin/members")
    public String members(Model model, HttpServletRequest request) {

        log.info("관리자 페이지 접근");

        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/members";

    }

    @MySecured(role = Role.STAFF)
    @GetMapping("/admin/members/{memberId}")
    public String member(@PathVariable Long memberId, Model model) {

        log.info("회원 정보");

        Member member = memberService.findOne(memberId);

        model.addAttribute("member", member);
        return "members/memberInfo";

    }

    @MySecured(role = Role.STAFF)
    @GetMapping("/admin/members/{memberId}/edit")
    public String updateForm(@PathVariable Long memberId, Model model) {

        log.info(">>> Show Update Form");

        Member member = memberService.findOne(memberId);

        model.addAttribute("member", member);
        model.addAttribute("roles", Role.values());
        return "members/update";

    }

    @MySecured(role = Role.STAFF)
    @PostMapping("/admin/members/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @Valid @ModelAttribute("member") Member form, BindingResult bindingResult){

        log.info("회원 정보 수정 진행");

        duplicationCheckUpdateLoginId(form, bindingResult); //중복 아이디 검증

        if (bindingResult.hasErrors()) {
            log.info("검증 에러 errors={}", bindingResult);
            return "members/update";
        }

        memberService.update(memberId, form);

        return "redirect:/admin/members";

    }

    private void duplicationCheckUpdateLoginId(Member member, BindingResult bindingResult) {

        if (memberRepository.checkDuplicatedLoginId(member.getId(), member.getLoginId()) != null) {
            log.info(">>> 로그인 ID 중복");
            bindingResult.rejectValue("loginId", "overlap");
        }

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
