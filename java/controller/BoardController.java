package kr.or.dcca.dcca.controller;

import kr.or.dcca.dcca.domain.Board;
import kr.or.dcca.dcca.repository.BoardRepository;
import kr.or.dcca.dcca.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    /*
     * 게시글 목록
     */
    @GetMapping("board/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "board/notifyList";
    }

    /*
     * 게시글 상세 및 등록 폼 호출
     */
    @GetMapping("board")
    public String board(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("board", boardService.findBoardById(id));
        return "board/notify";
    }

    /*
     * 게시글 생성
     */
    @PostMapping("board/create")
    public ResponseEntity<?> postBoard(@RequestBody Board board) {
        board.setCreateDate(LocalDateTime.now());
        board.setUpdateDate(LocalDateTime.now());
        boardRepository.save(board);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    /*
     * 게시글 수정
     */
    @PutMapping("board/{id}")
    public ResponseEntity<?> putBoard(@PathVariable("id") Long idx, @RequestBody Board board) {
        Board updateBoard = boardRepository.getOne(idx);
        updateBoard.setTitle(board.getTitle());
        updateBoard.setContent(board.getContent());
        updateBoard.setUpdateDate(LocalDateTime.now());
        boardRepository.save(updateBoard);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("board/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long idx) {
        boardRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
