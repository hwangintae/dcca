package com.intaehwang.springtanz.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardForm {

    private Long id;
    private String title;
    private String content;
    private String writer;
}
