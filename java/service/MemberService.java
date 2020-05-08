package kr.or.dcca.dcca.service;

import kr.or.dcca.dcca.domain.Member;
import kr.or.dcca.dcca.domain.Role;
import kr.or.dcca.dcca.repository.MemberJpaRepository;
import kr.or.dcca.dcca.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void joinV1(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        log.info("memberService joinV1 내부");
        memberJpaRepository.save(member);
    }

    @Transactional
    public Member findByEmail(String email) {
        Member findMember = memberJpaRepository.findByEmail(email);
        return findMember;
    }



    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//        Member findMember = memberJpaRepository.findByEmail(userEmail);
        Optional<Member> findMember = memberRepository.findByEmail(userEmail);

        Member findMemberEntity = findMember.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getKey()));

        return new User(findMemberEntity.getEmail(), findMemberEntity.getPassword(), authorities);
    }


}
