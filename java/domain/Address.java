package kr.or.dcca.dcca.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
