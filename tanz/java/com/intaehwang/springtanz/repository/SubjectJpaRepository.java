package com.intaehwang.springtanz.repository;

import com.intaehwang.springtanz.domain.Subject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SubjectJpaRepository {

    @PersistenceContext
    private EntityManager em;


}
