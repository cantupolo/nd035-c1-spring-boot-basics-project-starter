package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping()
    public String view(Model model,
                       @RequestParam(name = "errorMsg", required = false)
                               String errorMsg,
                       @RequestParam(name = "successMsg", required = false)
                                   String successMsg,
                       @RequestParam(name = "activeTab", required = false, defaultValue = "files")
                                   String activeTab) {
        if (errorMsg != null) {
            model.addAttribute("errorMsg", errorMsg);
        }
        if (successMsg != null) {
            model.addAttribute("successMsg", successMsg);
        }
        model.addAttribute("activeTab", activeTab);

        return "result";
    }

}
