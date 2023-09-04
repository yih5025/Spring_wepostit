package com.example.APITesting.controller;


import com.example.APITesting.domain.Member;
import com.example.APITesting.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class HomeController {
    private final MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String Home() {
        System.out.println("index");
        return "index";
    }

    @GetMapping("about")
    public String About() {
        System.out.println("about");
        return "about";

    }

    @GetMapping("post-page")
    public String Messages(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        System.out.println("post-page");

        return "post-page";
    }

    @PostMapping("/result")
    public String submit(@RequestParam("name") String name, @RequestParam("message") String message, Model model, MemberForm form) {
        // 전달할 데이터를 GET 파라미터로 설정
        model.addAttribute("name", name);
        model.addAttribute("message", message);
        System.out.println("--------------------------result post--------------------------");
        System.out.println("name: " + name + " message: " + message);

        Member member = new Member();
        member.setName(form.getName());
        member.setMessage(form.getMessage());

        System.out.println("member name = " + member.getName() + " member message = " + member.getMessage());

        memberService.join(member);

        // GET 메소드로 리다이렉트
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        return "redirect:/get-result?name=" + name + "&message=" + encodedMessage;
    }

    @GetMapping("get-result")
    public String Result(@RequestParam("name") String name,
                         @RequestParam("message") String message,
                         Model model) {
        model.addAttribute("name", name);
        model.addAttribute("message", message);

        return "result";
    }

}
