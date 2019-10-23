package com.test.privat.currency.models.dtolayer.converter;

import com.test.privat.currency.models.dtolayer.wrappers.UserDetailsWrapper;
import com.test.privat.currency.models.entities.Currency;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDetailsDTOConverter implements DTOConverter<User, UserDetailsWrapper> {

    private final UserService userService;

    private static final String NOT_FOUND_CURRENCY_TEXT = "Unselected";

    @Autowired
    public UserDetailsDTOConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User forward(UserDetailsWrapper dto) {
        return userService.getByLogin(dto.getLogin());
    }

    @Override
    public UserDetailsWrapper backward(User entity) {
        UserDetailsWrapper userDetailsWrapper = new UserDetailsWrapper();
        userDetailsWrapper.setLogin(entity.getLogin());
        userDetailsWrapper.setEmail(entity.getEmail());

        Currency userCurrency = entity.getValueCurrency();

        String userCurrencyKey;

        if(Objects.isNull(userCurrency)){
            userCurrencyKey = NOT_FOUND_CURRENCY_TEXT;
        } else {
            userCurrencyKey = userCurrency.getKey();
        }

        userDetailsWrapper.setCurrencyKey(userCurrencyKey);
        return userDetailsWrapper;
    }
}
