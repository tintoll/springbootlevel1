package io.tintoll.springbootlevel1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/usersample")
    public String usersample() {
        return "usersample";
    }
}
