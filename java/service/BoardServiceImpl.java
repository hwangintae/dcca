package kr.or.dcca.dcca.service;

import kr.or.dcca.dcca.domain.Board;
import kr.or.dcca.dcca.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ?
                0 : pageable.getPageNumber()-1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(new Board());
    }
}
