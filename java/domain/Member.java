package kr.or.dcca.dcca.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    private int age;

    @Embedded
    private Address address;
}
