package com.intaehwang.springtanz.service;

import com.intaehwang.springtanz.domain.Course;
import com.intaehwang.springtanz.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public void save(Course course) {
        List<Course> all = courseRepository.findAll();
        int tmp = all.size()+1;
        course.setId("TANZCo" + Integer.toString(tmp));
        courseRepository.save(course);
    }

    public void deleteCourseList(String id) {
        courseRepository.deleteById(id);
    }

    public Page<Course> findCourseList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ?
                0 : pageable.getPageNumber()-1,
                pageable.getPageSize());

        return courseRepository.findAll(pageable);
    }

    public Optional<Course> findById(String id) {
         return courseRepository.findById(id);
    }
}