package com.intaehwang.springtanz.controller;

import com.intaehwang.springtanz.domain.Course;
import com.intaehwang.springtanz.service.CourseService;
import com.intaehwang.springtanz.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final SubjectService subjectService;

    @GetMapping("course/new")
    public String create(Model model) {
        model.addAttribute("courseForm", new CourseForm());
        return "course/createCourseForm";
    }
    @PostMapping("course/new")
    public String create(@Valid CourseForm form) {

        Course course = Course.builder()
                .courseName(form.getCourseName())
                .courseStart(form.getCourseStart().toString())
                .courseEnd(form.getCourseEnd().toString())
                .createSubjectNum(0)
                .joinNum(0)
                .grade(form.getGrade())
                .build();

        courseService.save(course);

        return "redirect:/course/list";
    }

    // 과정 리스트
    @GetMapping("course/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        Page<Course> courseList = courseService.findCourseList(pageable);
        model.addAttribute("courseList", courseList);
        return "course/list";
    }

    // 과정 조회
    @GetMapping("courseDetail/{id}")
    public String detail(@PathVariable("id") String id, Model model) {
        Optional<Course> findId = courseService.findById(id);
        Course findCourse = findId.get();

        model.addAttribute("findCourse", findCourse);

        return "course/courseDetail";
    }
}
