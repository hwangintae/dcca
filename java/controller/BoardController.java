package kr.or.dcca.dcca.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import kr.or.dcca.dcca.config.auth.dto.BoardDto;
import kr.or.dcca.dcca.domain.Board;
import kr.or.dcca.dcca.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시판 목록
    @GetMapping("board/list")
    public String list(Model model) {
        List<BoardDto> boards = boardService.getBoardList();

        model.addAttribute("boardList", boards);
        return "board/notifyList";
    }

    // 글 생성
    @GetMapping("board/create")
    public String create(Model model) {
        model.addAttribute("boardForm", new Board());
        return "board/notify";
    }
    @PostMapping("board/create")
    public String create(BoardDto boardDto) {
        boardService.save(boardDto);
        return "redirect:/board/list";
    }
    // end

    // 글 조회
    @GetMapping("board/notify/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getBoard(no);

        model.addAttribute("boardDto", boardDto);
        return "board/notifyDetail";
    }

    // 글 수정
    @GetMapping("board/notify/modify/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getBoard(no);

        model.addAttribute("boardDto", boardDto);
        return "board/notifyUpdate";
    }

    @PutMapping("board/notify/modify/{no}")
    public String update(BoardDto boardDto) {
        boardService.save(boardDto);
        return "redirect:/board/list";
    }
    // end

    // 글 삭제
    @DeleteMapping("board/notify/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deleteBoard(no);

        return "redirect:/board/list";
    }
}
