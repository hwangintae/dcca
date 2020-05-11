package kr.or.dcca.dcca.service;

import kr.or.dcca.dcca.config.auth.dto.BoardDto;
import kr.or.dcca.dcca.domain.Board;
import kr.or.dcca.dcca.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public List<BoardDto> getBoardList() {
        List<Board> findBoard = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : findBoard) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createdDate(board.getCreatedDate())
                    .build();

            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    public Long save(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    public BoardDto getBoard(Long id) {
        Optional<Board> boardWrapper = boardRepository.findById(id);
        Board findBoard = boardWrapper.get();

        return BoardDto.builder()
                .id(findBoard.getId())
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .writer(findBoard.getWriter())
                .createdDate(findBoard.getCreatedDate())
                .build();
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
