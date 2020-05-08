package kr.or.dcca.dcca.controller;

import kr.or.dcca.dcca.domain.Address;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class MemberForm {

    private String email;
    private String password;

    private String userName;
    private Date birth;


    private int zipcode;

    private String firAdd;
    private String secAdd;
    private String etc;
}
