package kr.or.dcca.dcca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 회장 인사말
    @GetMapping("association/hello")
    public String hello() {
        return "association/intro";
    }

    // 설립 취지
    @GetMapping("association/goal")
    public String goal() {
        return "association/intro-alt1";
    }

    // 협회 연혁
    @GetMapping("association/history")
    public String history() {
        return "association/intro-alt2";
    }

    // 협회 조직도
    @GetMapping("association/chart")
    public String chart() {
        return "association/intro-alt3";
    }

    // 해외협력 사업
    @GetMapping("business/cowork")
    public String work() {
        return "business/intro2";
    }

    // WRO 대회
    @GetMapping("business/wro")
    public String WRO() {
        return "business/intro2-alt1";
    }

    // 디지털 문화 융합 세미나
    @GetMapping("business/seminar")
    public String seminar() {
        return "business/intro2-alt2";
    }

    // 협회 갤러리
    @GetMapping("board/pic")
    public String gallery() {
        return "board/gallery";
    }

    // 자 료 실
    @GetMapping("board/repo")
    public String data() {
        return "board/data";
    }
//
//    // 회원가입
//    @GetMapping("login/signup")
//    public String signup() {
//        return "login/signup";
//    }
//
//    // 로그인
//    @GetMapping("login/signin")
//    public String signin() {
//        return "login/signin";
//    }
}
