package com.intaehwang.springtanz.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private String userName;
    private int grade;

    @Enumerated(EnumType.STRING)
    private UserTypeName userType;

    private Address address;

    public Member() {}

    @Builder
    public Member(String userId, String password, String userName, int grade, Address address) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.grade = grade;
        this.address = address;
    }

    @OneToMany
    public List<Exam> exams;
}
