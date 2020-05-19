package com.intaehwang.springtanz.controller;

import com.intaehwang.springtanz.domain.Board;
import com.intaehwang.springtanz.domain.Member;
import com.intaehwang.springtanz.domain.Subject;
import com.intaehwang.springtanz.service.MemberService;
import com.intaehwang.springtanz.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class SubjectController {

    @Autowired
    MemberService memberService;

    @Autowired
    SubjectService subjectService;

    // 과목 등록
    @GetMapping("/subject/new")
    public String createSubject(Model model) {
        List<Member> teacherCodeList = memberService.findTeacherCodeList();
        model.addAttribute("subjectForm", new SubjectForm())
            .addAttribute("findTeacher", teacherCodeList);
        return "subject/createSubjectForm";
    }

//    @PostMapping("subject/new")
//    public String create(@Valid SubjectForm form, BindingResult result) {
//        if (result.hasErrors()) return "subject/createSubjectForm";
//
//        Subject subject = new Subject();
//        subject.setYearMonth(form.getYearMonth());
//        subject.setSubjectName(form.getSubjectName());
//        subject.setSubjectGrade(form.getSubjectGrade());
//        subject.setTeacherCode(form.getTeacherCode());
//
//        subjectService.save(subject);
//
//        return "redirect:/";
//    }

    @GetMapping("subject/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        Page<Board> subjectList = subjectService.findSubjectList(pageable);
        model.addAttribute("subjectList", subjectList);
        return "subject/list";
    }
}
