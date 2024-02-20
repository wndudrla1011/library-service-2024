package com.rootable.practiceJwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

    public static String ADMIN_KEY = "admin";

    @GetMapping("/")
    public String homeLogin() {

        log.info("홈 화면 이동");

        return "Home Controller";

    }

}
