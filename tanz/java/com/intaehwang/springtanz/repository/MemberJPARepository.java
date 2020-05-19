package com.intaehwang.springtanz.repository;

import com.intaehwang.springtanz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJPARepository extends JpaRepository<Member, String> {
}
