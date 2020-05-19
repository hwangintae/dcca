package com.intaehwang.springtanz.service;

import com.intaehwang.springtanz.controller.MemberForm;
import com.intaehwang.springtanz.domain.Address;
import com.intaehwang.springtanz.domain.Member;
import com.intaehwang.springtanz.domain.Role;
import com.intaehwang.springtanz.domain.UserTypeName;
import com.intaehwang.springtanz.repository.MemberJPARepository;
import com.intaehwang.springtanz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberJPARepository memberJPARepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void join(Member member) {
        validateDuplicateMember(member);

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        if (member.getUserId().equals("admin")) {
            member.setRole(Role.ADMIN);
            member.setUserType(UserTypeName.ADMIN);
        }
        else {
            member.setRole(Role.STUDENT);
            member.setUserType(UserTypeName.STUDENT);
        }
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findOne(member.getUserId());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 ID 입니다.");
        }
    }

    // 회원 조회
    public Member findOne(String userId) {
        return memberRepository.findOne(userId);
    }
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    public List<Member> findByUserName(String userName) {
        return memberRepository.findByUserName(userName);
    }
    public List<Member> findTeacherCodeList() {
        return memberRepository.findTeacherCodeList();
    }
    public List<Member> findStudentList() {
        return memberRepository.findStudentList();
    }


    // 회원 수정
    @Transactional
    public void updateUser(MemberForm form) {
        Member findMember = memberRepository.findOne(form.getUserId());

        Address address = new Address(form.getZipcode(), form.getFirAdd(), form.getSecAdd(), form.getEtc());

        findMember.setUserName(form.getUserName());
        findMember.setGrade(form.getGrade());
        findMember.setAddress(address);
    }

    @Transactional
    public void updateUserType(String userId) {
        Member member = memberRepository.findOne(userId);

        if (member.getUserType().equals(UserTypeName.STUDENT)) {
            member.setUserType(UserTypeName.TEACHER);
            member.setRole(Role.TEACHER);
        }
        else if (member.getUserType().equals(UserTypeName.TEACHER) ) {
            member.setUserType(UserTypeName.STUDENT);
            member.setRole(Role.STUDENT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberService that = (MemberService) o;
        return Objects.equals(memberRepository, that.memberRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> findId = memberJPARepository.findById(userId);

        Member findMember = findId.get();

//        Member findMember = memberRepository.findOne(userId);


        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<>();
                if (findMember.getRole().equals(Role.ADMIN))
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                else if (findMember.getRole().equals(Role.TEACHER))
                    authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
                else
                    authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                return authorities;
            }

            @Override
            public String getPassword() { return findMember.getPassword(); }

            @Override
            public String getUsername() {
                return findMember.getUserId();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        return userDetails;
    }

}
