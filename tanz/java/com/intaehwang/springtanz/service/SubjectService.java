package com.intaehwang.springtanz.service;

import com.intaehwang.springtanz.domain.Board;
import com.intaehwang.springtanz.domain.Subject;
import com.intaehwang.springtanz.repository.SubjectJpaRepository;
import com.intaehwang.springtanz.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectJpaRepository subjectJpaRepository;

    public void save(Subject subject){
        subjectRepository.save(subject);
    }

    public Page<Board> findSubjectList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ?
                0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());
        return subjectRepository.findALlByOrderByIdDesc(pageable);
    }
}
