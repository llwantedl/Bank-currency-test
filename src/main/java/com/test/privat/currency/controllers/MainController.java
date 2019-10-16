package com.test.privat.currency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage(ModelMap modelMap) {
        modelMap.addAttribute("msg", "Ello");
        return "main";
    }

}
