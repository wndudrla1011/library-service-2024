package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.service.MemberService;
import com.rootable.libraryservice2022.web.MySecured;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;

    @MySecured(role = Role.ADMIN)
    @GetMapping("/admin")
    public String admin(Model model, HttpServletRequest request) {

        log.info("관리자 페이지 접근");

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        model.addAttribute("member", member);
        return "loginHomeAdmin";

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



}
