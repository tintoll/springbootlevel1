package io.tintoll.springbootlevel1.controller;

import io.tintoll.springbootlevel1.domain.Member;
import io.tintoll.springbootlevel1.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired private MemberService service;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("list", service.findAll());
        model.addAttribute("member",new Member());

        return "member";
    }

    @PostMapping
    public String create(@Valid Member member, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("member",member);
            return "member";
        }

        service.save(member);

        return "redirect:/member";
    }
}
