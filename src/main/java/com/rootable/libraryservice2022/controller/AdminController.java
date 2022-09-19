package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Role;
import com.rootable.libraryservice2022.web.MySecured;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AdminController {

    @MySecured(role = Role.ADMIN)
    @GetMapping("/admin")
    public String admin(Model model, HttpServletRequest request) {

        log.info("관리자 페이지 접근");

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        model.addAttribute("member", member);
        return "loginHomeAdmin";

    }

}
