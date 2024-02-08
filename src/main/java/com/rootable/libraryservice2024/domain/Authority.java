package com.rootable.libraryservice2024.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    ADMIN("ROLE_ADMIN", "관리자"),
    STAFF("ROLE_STAFF", "직원"),
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "사용자");

    private final String key;
    private final String title;

}