package com.rootable.libraryservice2022.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String field;
    private String message;

    @Builder
    public LoginResponseDto(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
