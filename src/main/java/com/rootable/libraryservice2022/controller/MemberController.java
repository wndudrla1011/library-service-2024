package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/add")
    public String joinForm(@ModelAttribute("member") Member member) {
        log.info(">>> Show Join Form");
        return "members/signup";
    }

}
