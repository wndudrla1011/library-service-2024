package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.web.MySecured;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final HttpSession httpSession;

    @MySecured(role = Role.ADMIN)
    @GetMapping("/admin")
    public String admin(Model model) {

        log.info("관리자 페이지 접근");

        //세션 획득
        Member member = (Member) httpSession.getAttribute("loginMember");

        model.addAttribute("member", member);
        return "loginHomeAdmin";

    }

}
