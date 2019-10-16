package com.test.privat.currency.models.services;

import com.test.privat.currency.models.UserCredentials;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.exceptions.NoRemoteUserException;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    User getRemoteUser() throws NoRemoteUserException;
    void login(UserCredentials userCredentials);
}
