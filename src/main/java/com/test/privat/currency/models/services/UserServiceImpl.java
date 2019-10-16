package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.UserForm;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.repositories.CurrencyRepository;
import com.test.privat.currency.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CurrencyRepository currencyRepository;

    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           CurrencyRepository currencyRepository,
                           RoleService roleService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(UserForm userForm) {
        User user = new User();

        String username = userForm.getUsername();
        String password = passwordEncoder.encode(userForm.getPassword());
        String email = userForm.getEmail();
        String currency = userForm.getCurrencyKey();

        user.setLogin(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(roleService.getUserRole());
        user.setValueCurrency(currencyRepository.getByKey(currency));

        return user;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
