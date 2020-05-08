package kr.or.dcca.dcca.service;

import kr.or.dcca.dcca.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Page<Board> findBoardList(Pageable pageable);
    Board findBoardById(Long id);
}
