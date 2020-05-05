package kr.or.dcca.dcca.service;

import kr.or.dcca.dcca.domain.Member;
import kr.or.dcca.dcca.repository.MemberJpaRepository;
import kr.or.dcca.dcca.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    public boolean chkUserId(String userId) {
        Optional<Member> findUserId = memberRepository.findByUserId(userId);

        if (findUserId == null) return true;
        else return false;
    }

    @Transactional
    public void joinV1(Member member) {
        log.info("memberService joinV1 내부");
        memberJpaRepository.save(member);
    }
}
