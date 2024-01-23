package com.rootable.libraryservice2024.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    PERMISSION("발행 중인 도서"),
    DENIED("절판 도서");

    private final String title;

}
