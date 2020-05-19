package com.intaehwang.springtanz.service;

import com.intaehwang.springtanz.domain.Board;
import com.intaehwang.springtanz.dto.BoardDto;
import com.intaehwang.springtanz.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public void updateBoard(BoardDto boardDto) {
        Optional<Board> findId = boardRepository.findById(boardDto.getId());
        Board board = findId.get();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }


    public List<BoardDto> searchKeyword(String keyword) {

        List<Board> boardList = boardRepository.findByTitle(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();


        if (boardList.isEmpty()) return boardDtoList;

        for (Board board : boardList) {
            boardDtoList.add(BoardDto
                    .builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writer(board.getWriter())
                        .createdDate(board.getCreatedDate())
                    .build());
        }
        return boardDtoList;
    }

    public Page<Board> findBordList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ?
                0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());

        return boardRepository.findALlByOrderByIdDesc(pageable);
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(new Board());
    }
}
