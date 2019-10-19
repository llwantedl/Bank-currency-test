package com.test.privat.currency.controllers;

import com.test.privat.currency.models.security.Credentials;
import com.test.privat.currency.models.dtolayer.wrappers.UserForm;
import com.test.privat.currency.models.services.AuthenticationService;
import com.test.privat.currency.models.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    private final UserValidator userValidator;

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserValidator userValidator,
                                    AuthenticationService authenticationService) {
        this.userValidator = userValidator;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String getLoginPage(ModelMap modelMap) {
        modelMap.put("creds", new Credentials());
        return "login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(ModelMap modelMap) {
        modelMap.put("userForm", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String postRegistration(@ModelAttribute("userForm") UserForm userForm,
                                   ModelMap modelMap,
                                   BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/register";
        }

        authenticationService.register(userForm);

        return "redirect:/";
    }
}
