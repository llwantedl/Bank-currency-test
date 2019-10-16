package com.test.privat.currency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String getLoginPage(ModelMap modelMap) {
        return "login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(ModelMap modelMap) {
        return "register";
    }
}
