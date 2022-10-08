package com.rootable.libraryservice2022.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final Environment env; //active 상태 profile 집합

    @GetMapping("/profile")
    public String profile() {

        //active 프로파일 추출
        List<String> profiles = Arrays.asList(env.getActiveProfiles());

        //배포용 profiles
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");

        //default profile 지정
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        //가장 먼저 찾은 "real"이 포함된 profile 반환
        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile); //없을 경우, default profile 반환

    }

}
