package com.intaehwang.springtanz.repository;

import com.intaehwang.springtanz.domain.Board;
import com.intaehwang.springtanz.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Page<Board> findALlByOrderByIdDesc(Pageable pageable);
}
