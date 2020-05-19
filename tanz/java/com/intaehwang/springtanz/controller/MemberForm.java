package com.intaehwang.springtanz.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    private String userId;
    private String userName;
    private String password;
    private int grade;

    private int zipcode;

    private String firAdd;
    private String secAdd;
    private String etc;
}
