package com.rootable.libraryservice2022.web.advice;

import com.rootable.libraryservice2022.controller.LoginController;
import com.rootable.libraryservice2022.web.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(basePackageClasses = LoginController.class)
public class LoginControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<LoginResponseDto>> argumentNotValidException(MethodArgumentNotValidException ex) {

        List<LoginResponseDto> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            errors.add(new LoginResponseDto(field, message));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<LoginResponseDto> invalidMemberException() {

        LoginResponseDto errorInfo = new LoginResponseDto("global", "로그인 정보가 일치하지 않습니다.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);

    }

}
