package kr.or.dcca.dcca.controller;

import kr.or.dcca.dcca.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    private String userId;
    private String password;

    private String userName;
    private int age;
    private String email;

    private int zipcode;

    private String firAdd;
    private String secAdd;
    private String etc;
}
