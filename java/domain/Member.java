package kr.or.dcca.dcca.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    private Date birth;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(String email, String password, String userName, Date birth, Address address, Role role) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.birth = birth;
        this.address = address;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
