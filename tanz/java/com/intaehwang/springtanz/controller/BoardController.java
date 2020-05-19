package com.intaehwang.springtanz.controller;

import com.intaehwang.springtanz.domain.Board;
import com.intaehwang.springtanz.dto.BoardDto;
import com.intaehwang.springtanz.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시판 목록
    @GetMapping("board/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        Page<Board> bordList = boardService.findBordList(pageable);
        model.addAttribute("boardList", bordList);
        return "board/notify/notifyList";
    }

    // 글 생성
    @GetMapping("board/create")
    public String create(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "board/notify/createNotifyForm";
    }
    @PostMapping("board/create")
    public String create(BoardDto boardDto) {
        boardService.save(boardDto);
        return "redirect:/board/list";
    }


    // 글 조회
    @GetMapping("board/notify/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getBoard(no);

        model.addAttribute("boardDto", boardDto);
        return "board/notify/notifyDetail";
    }

    @PutMapping("board/notify/modify")
    public String update(BoardDto boardDto) {
        boardService.updateBoard(boardDto);
        return "redirect:/board/list";
    }

    @PutMapping("board/notify/modify/{no}")
    public String modify(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getBoard(no);

        model.addAttribute("boardDto", boardDto);
        return "board/notify/notifyModi";
    }



    // 글 삭제
    @PutMapping("board/notify/del/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deleteBoard(no);

        return "redirect:/board/list";
    }

    // 글 제목 검색
    @GetMapping("board/notify/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchKeyword(keyword);

        if (boardDtoList.isEmpty()) return "redirect:/board/list";

        model.addAttribute("boardList", boardDtoList);

        return "board/notify/notifySearchList";
    }
}