package com.rootable.libraryservice2024.jwt.config;

import com.rootable.libraryservice2024.jwt.handler.JwtAccessDeniedHandler;
import com.rootable.libraryservice2024.jwt.handler.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    //PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                //token 방식 => off the csrf/form login/httpBasic
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                //세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //HttpServletRequest를 사용하는 요청들에 대한 접근제한 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll() //홈
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll() //로그인 api
                        .requestMatchers(new AntPathRequestMatcher("/members/add")).permitAll() //회원가입 api
                        .requestMatchers(new AntPathRequestMatcher("/admin")).hasRole("ADMIN") //관리자 페이지에 요구되는 권한 설정
                        .anyRequest().authenticated()); //그 외 인증 없이 접근x

        return httpSecurity.build();

    }

}
