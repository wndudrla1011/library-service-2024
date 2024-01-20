package com.rootable.libraryservice2022.web.advice;

import com.rootable.libraryservice2022.web.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BadRequestAdvice {

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
}
