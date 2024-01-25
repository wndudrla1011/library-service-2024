package com.rootable.libraryservice2024.web.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String loginId;
    private String password;

}
