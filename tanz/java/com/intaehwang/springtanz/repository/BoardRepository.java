package com.intaehwang.springtanz.repository;

import com.intaehwang.springtanz.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String keyword);

    Page<Board> findALlByOrderByIdDesc(Pageable pageable);
}
