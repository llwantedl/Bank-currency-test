package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.UserForm;
import com.test.privat.currency.models.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User create(UserForm userForm);

    User getByLogin(String login);

    User getByEmail(String email);
}
