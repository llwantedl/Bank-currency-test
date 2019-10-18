package com.test.privat.currency.models.services;

import com.test.privat.currency.models.Credentials;
import com.test.privat.currency.models.dtolayer.wrappers.UserForm;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    User getRemoteUser() throws UserNotFoundException;
    void login(Credentials credentials);
    void register(UserForm userForm);
}
