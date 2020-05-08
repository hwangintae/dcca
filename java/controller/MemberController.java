package kr.or.dcca.dcca.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import kr.or.dcca.dcca.domain.Address;
import kr.or.dcca.dcca.domain.Member;
import kr.or.dcca.dcca.domain.Role;
import kr.or.dcca.dcca.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원가입
    @GetMapping("member/new")
    public String signUp(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/log/signup";
    }

    @PostMapping("member/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) return "member/log/signup";
        Address address = new Address(form.getZipcode(), form.getFirAdd(), form.getSecAdd(), form.getEtc());
        Member member = Member.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .userName(form.getUserName())
                .birth(form.getBirth())
                .address(address)
                .role(Role.MEMBER).build();

        memberService.joinV1(member);

        return "redirect:/";
    }

    // 로그인
    @GetMapping("member/log/login")
    public String signIn(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/log/signin";
    }
}
