package com.test.privat.currency.models.services;

import com.test.privat.currency.BankUserDetails;
import com.test.privat.currency.models.UserCredentials;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.exceptions.NoRemoteUserException;
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
    public User getRemoteUser() throws NoRemoteUserException {
        Object userDetailsObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetailsObj instanceof BankUserDetails)) {
            throw new NoRemoteUserException("No remote user details was found");
        }
        BankUserDetails userDetails = (BankUserDetails) userDetailsObj;
        String username = userDetails.getUsername();

        User remoteUser = userService.getByLogin(username);

        if(Objects.isNull(remoteUser)){
            throw new NoRemoteUserException("No user found by current user details");
        }

        return remoteUser;
    }

    @Override
    public void login(UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        BankUserDetails user = (BankUserDetails) userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, userCredentials.getPassword(), user.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            logger.debug("User " + username + " successfully logged in");
        }
    }
}
