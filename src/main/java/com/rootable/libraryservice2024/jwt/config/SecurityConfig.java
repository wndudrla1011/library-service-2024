package com.rootable.libraryservice2024.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //폼 기반 로그인 비활성화
        httpSecurity.formLogin().disable();

        //HTTP 기본 인증 비활성화
        httpSecurity.httpBasic().disable();

        //CSRF 공격 방어 기능 비활성화
        httpSecurity.csrf().disable();

        /*
        * 세션 관리 정책 설정
        * 세션 인증 사용x => JWT를 사용하여 인증
        * */
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return httpSecurity.build();

    }

}
