package com.intaehwang.springtanz.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN"),
    TEACHER("ROLE_TEACHER"),
    STUDENT("ROLE_STUDENT");

    private final String key;
}
