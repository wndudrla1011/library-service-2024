package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.web.argumentresolver.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@Login Member loginMember, Model model) {

        log.info("홈 화면 이동");

        //세션에 회원 데이터가 없으면 intro 화면으로
        if (loginMember == null) {
            return "intro";
        }

        //회원 데이터가 있으면 로그인 화면으로
        model.addAttribute("member", loginMember);
        return "loginHome";

    }

}
