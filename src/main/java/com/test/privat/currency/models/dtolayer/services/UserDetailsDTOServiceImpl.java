package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.converter.DTOConverter;
import com.test.privat.currency.models.dtolayer.wrappers.UserDetailsWrapper;
import com.test.privat.currency.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsDTOServiceImpl implements UserDetailsDTOService {

    private final DTOConverter<User, UserDetailsWrapper> userConverter;

    @Autowired
    public UserDetailsDTOServiceImpl(DTOConverter<User, UserDetailsWrapper> converter) {
        this.userConverter = converter;
    }

    @Override
    public UserDetailsWrapper getUserDetails(User user) {
        return userConverter.backward(user);
    }

    @Override
    public List<UserDetailsWrapper> convertListToDTO(List<User> entities) {
        return entities
                .stream()
                .map(userConverter::backward)
                .collect(Collectors.toList());
    }
}
