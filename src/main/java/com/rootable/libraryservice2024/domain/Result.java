package com.rootable.libraryservice2024.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Result {

    SUCCESS("성공", "정상 처리되었습니다."),
    FAIL("실패", "재고 부족으로 신청 불가합니다."),
    DENIED("실패", "절판 도서입니다.");

    private final String result;
    private final String reason;

}
