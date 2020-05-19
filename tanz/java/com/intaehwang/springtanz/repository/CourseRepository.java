package com.intaehwang.springtanz.repository;

import com.intaehwang.springtanz.domain.Board;
import com.intaehwang.springtanz.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {

}
