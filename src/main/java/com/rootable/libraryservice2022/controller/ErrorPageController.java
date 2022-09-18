package com.rootable.libraryservice2022.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ErrorPageController {

    @GetMapping("/error-403")
    public void error403(HttpServletResponse response) throws IOException {

        log.info("권한 부족으로 403 에러 발생");
        response.sendError(403, "403 에러");

    }

}
