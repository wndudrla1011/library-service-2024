package com.rootable.libraryservice2024.controller;

import com.rootable.libraryservice2024.domain.Member;
import com.rootable.libraryservice2024.domain.Role;
import com.rootable.libraryservice2024.jwt.constants.SecurityConstants;
import com.rootable.libraryservice2024.jwt.prop.JwtProp;
import com.rootable.libraryservice2024.service.LoginService;
import com.rootable.libraryservice2024.web.dto.LoginDto;
import com.rootable.libraryservice2024.web.dto.SessionMember;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final HttpSession httpSession;
    private final JwtProp jwtProp;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginDto dto) {
        log.info("로그인 폼 이동");
        return "login/signIn";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        log.info("로그인 검증");

        String loginId = loginDto.getLoginId();
        String password = loginDto.getPassword();

        log.info("loginId : " + loginId);
        log.info("password : " + password);

        List<String> roles = new ArrayList();
        roles.add(Role.ADMIN.getKey());
        roles.add(Role.STAFF.getKey());
        roles.add(Role.GUEST.getKey());
        roles.add(Role.USER.getKey());

        //secret key -> bytes
        byte[] signingKey = jwtProp.getSecretKey().getBytes();

        //토큰 생성
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, Keys.hmacShaKeyFor(signingKey))
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3)) //3 min
                .claim("uid", loginId)
                .claim("role", roles)
                .compact();

        log.info("jwt : " + jwt);

        return new ResponseEntity<>(jwt, HttpStatus.OK);

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
