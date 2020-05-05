package kr.or.dcca.dcca.repository;

import kr.or.dcca.dcca.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.PersistenceContext;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
}
