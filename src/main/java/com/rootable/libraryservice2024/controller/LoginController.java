package com.rootable.libraryservice2024.controller;

import com.rootable.libraryservice2024.service.LoginService;
import com.rootable.libraryservice2024.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final HttpSession httpSession;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginDto dto) {
        log.info("로그인 폼 이동");
        return "login/signIn";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        log.info("로그인 검증");

        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        log.info("username : " + username);
        log.info("password : " + password);

        return new ResponseEntity<>("login", HttpStatus.OK);

    }

    @PostMapping("/logout")
    public String logout() {

        log.info("로그아웃");

        //세션 삭제
        if (httpSession != null) {
            httpSession.invalidate();
        }

        return "redirect:/";

    }

}
