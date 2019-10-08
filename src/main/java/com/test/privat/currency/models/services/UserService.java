package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.UserForm;
import com.test.privat.currency.models.entities.User;

public interface UserService {
    User signUp(UserForm userForm);
}
