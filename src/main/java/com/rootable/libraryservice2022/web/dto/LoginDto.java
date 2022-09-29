package com.rootable.libraryservice2022.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

}
