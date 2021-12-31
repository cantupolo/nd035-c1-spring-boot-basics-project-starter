package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String view(Model model, HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        String signupSuccess = "";
        if (savedRequest != null) {
            String[] vSignupSuccess = savedRequest.getParameterValues("signupSuccess");
            if (vSignupSuccess != null && vSignupSuccess.length > 0) {
                signupSuccess = vSignupSuccess[0];
            }
        }
        model.addAttribute("signupSuccess", signupSuccess);
        return "login";
    }

    @GetMapping("/logout")
    public String logoutView(){
        return "redirect:/login?logout";
    }
}
