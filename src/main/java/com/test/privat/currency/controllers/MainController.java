package com.test.privat.currency.controllers;

import com.test.privat.currency.models.dtolayer.services.UserDetailsDTOService;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.exceptions.UserNotFoundException;
import com.test.privat.currency.models.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final AuthenticationService authenticationService;
    private final UserDetailsDTOService userDetailsDTOService;

    @Autowired
    public MainController(AuthenticationService authenticationService,
                          UserDetailsDTOService userDetailsDTOService) {
        this.authenticationService = authenticationService;
        this.userDetailsDTOService = userDetailsDTOService;
    }

    @GetMapping("/")
    public String getMainPage(ModelMap modelMap) throws UserNotFoundException {
        User remoteUser = authenticationService.getRemoteUser();
        modelMap.addAttribute("remoteUser", userDetailsDTOService.getUserDetails(remoteUser));
        return "main";
    }
}
