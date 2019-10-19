package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.wrappers.UserDetailsWrapper;
import com.test.privat.currency.models.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsDTOService extends DTOService<User, UserDetailsWrapper> {
    UserDetailsWrapper getUserDetails(User user);
}
