package com.test.privat.currency.models.services;

import com.test.privat.currency.models.security.BankUserDetails;
import com.test.privat.currency.models.security.Credentials;
import com.test.privat.currency.models.dtolayer.wrappers.UserForm;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     UserDetailsService userDetailsService,
                                     AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User getRemoteUser() throws UserNotFoundException {
        Object userDetailsObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetailsObj instanceof BankUserDetails)) {
            throw new UserNotFoundException("No remote user details was found");
        }
        BankUserDetails userDetails = (BankUserDetails) userDetailsObj;

        return userDetails.getUser();
    }

    @Override
    public boolean isRemoteUser(User user) throws UserNotFoundException {
        User remoteUser = getRemoteUser();

        return !Objects.isNull(user) &&
                user.getLogin().equals(remoteUser.getLogin());

    }

    @Override
    public void login(Credentials credentials) {
        String username = credentials.getLogin();
        BankUserDetails user = (BankUserDetails) userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, credentials.getPassword(), user.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            logger.debug("User " + username + " successfully logged in");
        }
    }

    @Override
    public void register(UserForm userForm) {
        userService.create(userForm);
        String login = userForm.getLogin();
        String password = userForm.getConfirmPassword();
        Credentials creds = Credentials.build(login, password);
        login(creds);
    }
}
