package com.rootable.libraryservice2022.controller;

import com.rootable.libraryservice2022.web.argumentresolver.Login;
import com.rootable.libraryservice2022.web.dto.SessionMember;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public SessionMember test(@Login SessionMember loginMember) {
        return loginMember;
    }

}
