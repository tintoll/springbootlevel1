package io.tintoll.springbootlevel1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}
