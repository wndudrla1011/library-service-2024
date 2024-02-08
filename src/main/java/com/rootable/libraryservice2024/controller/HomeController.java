package com.rootable.libraryservice2024.controller;

import com.rootable.libraryservice2024.domain.Role;
import com.rootable.libraryservice2024.web.argumentresolver.Login;
import com.rootable.libraryservice2024.web.dto.SessionMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

    public static String ADMIN_KEY = "admin";

    @GetMapping("/")
    public String homeLogin(@Login SessionMember loginMember, Model model) {

        log.info("홈 화면 이동");

        return "Home Controller";

    }

}
