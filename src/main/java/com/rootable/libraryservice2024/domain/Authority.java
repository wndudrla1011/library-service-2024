package com.rootable.libraryservice2024.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    ROLE_USER,
    ROLE_GUEST,
    ROLE_STAFF,
    ROLE_ADMIN

}
