package com.intaehwang.springtanz.controller;

import com.intaehwang.springtanz.domain.Address;
import com.intaehwang.springtanz.domain.Member;
import com.intaehwang.springtanz.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class MemberController {

    @Autowired
    MemberService memberService;

    // 회원가입
    @GetMapping("/new")
    public String createMember(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "createMemberForm";
    }
    @PostMapping("/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) return "createMemberForm";

        Address address = new Address(form.getZipcode(), form.getFirAdd(), form.getSecAdd(), form.getEtc());

        Member member = Member.builder()
                .userId(form.getUserId())
                .password(form.getPassword())
                .userName(form.getUserName())
                .grade(form.getGrade())
                .address(address)
                .build();

        memberService.join(member);

        return "redirect:/";
    }

    // 회원 조회 및 수정
    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> findMember = memberService.findStudentList();
        model.addAttribute("members", findMember);

        return "members/memberList";
    }
    @GetMapping("member/{userId}/edit")
    public String updateMemberForm(@PathVariable("userId") String userId, Model model) {
        Member findMember = memberService.findOne(userId);

        MemberForm form = new MemberForm();
        form.setUserId(findMember.getUserId());
        form.setUserName(findMember.getUserName());
        form.setGrade(findMember.getGrade());
        form.setZipcode(findMember.getAddress().getZipcode());
        form.setFirAdd(findMember.getAddress().getFirAdd());
        form.setSecAdd(findMember.getAddress().getSecAdd());
        form.setEtc(findMember.getAddress().getEtc());

        model.addAttribute("form", form);

        return "members/updateMemberForm";
    }
    @PostMapping(value = "/members/{userId}/edit")
    public String updateMember(@ModelAttribute("form") MemberForm form) {
        memberService.updateUser(form);
        return "redirect:/members";
    }

    // 교사 등록
    @GetMapping("/members/teacher/new")
    public String createTeacher(Model model) {
        List<Member> findMember = memberService.findStudentList();
        model.addAttribute("teacherForm", new MemberForm())
                .addAttribute("members", findMember);
        return "members/createTeacherForm";
    }
    @PostMapping(value = "/members/teacher/new/{userId}")
    public String updateUserTypeTeacher(@ModelAttribute("form") MemberForm form) {
        memberService.updateUserType(form.getUserId());
        return "redirect:/members/teacher";
    }

    // 교사 조회 및 자격 박탈
    @GetMapping("/members/teacher")
    public String teacherList(Model model) {
        List<Member> findTeacher = memberService.findTeacherCodeList();
        model.addAttribute("members", findTeacher);

        return "members/teacherList";
    }
    @GetMapping("/members/teacher/cancel/{userId}")
    public String cancel(@PathVariable("userId") String userId) {
        memberService.updateUserType(userId);

        return "redirect:/members/teacher";
    }

//
//    @PostMapping("/members/teacher/{userId}/cancel")
//    public String cancelTeacher(@PathVariable("userId") String userId) {
//        memberService.updateUserType(userId);
//        return "redirect:/members/teacherList";
//    }
}
