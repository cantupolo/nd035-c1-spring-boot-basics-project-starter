package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String view() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutView(){
        return "redirect:/login?logout";
    }
}
