package kr.or.dcca.dcca.config.auth.dto;

import kr.or.dcca.dcca.domain.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {
    private String userName;

    public SessionMember(Member member) {
        this.userName = member.getUserName();
    }

}
