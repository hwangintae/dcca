package com.intaehwang.springtanz.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private int zipcode;
    private String firAdd;
    private String secAdd;
    private String etc;

    protected Address() {}

    public Address(int zipcode, String firAdd, String secAdd, String etc) {
        this.zipcode = zipcode;
        this.firAdd = firAdd;
        this.secAdd = secAdd;
        this.etc = etc;
    }
}
