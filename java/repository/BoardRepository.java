package kr.or.dcca.dcca.repository;

import kr.or.dcca.dcca.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
