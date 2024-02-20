package com.rootable.practiceJwt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/admin")
    public String admin() {

        log.info("관리자 페이지 접근");

        return "Admin Controller";

    }

}
