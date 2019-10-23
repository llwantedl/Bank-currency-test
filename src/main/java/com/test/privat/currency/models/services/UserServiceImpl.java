package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.UserForm;
import com.test.privat.currency.models.dtolayer.wrappers.WalletForm;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.repositories.CurrencyRepository;
import com.test.privat.currency.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CurrencyRepository currencyRepository;

    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final WalletService walletService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           CurrencyRepository currencyRepository,
                           RoleService roleService,
                           BCryptPasswordEncoder passwordEncoder,
                           WalletService walletService) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.walletService = walletService;
    }

    @Override
    @Transactional
    public User create(UserForm userForm) {
        User user = new User();

        String login = userForm.getLogin();
        String password = passwordEncoder.encode(userForm.getPassword());
        String email = userForm.getEmail();
        String currency = userForm.getCurrencyKey();

        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(roleService.getUserRole());
        user.setValueCurrency(currencyRepository.getByKey(currency));

        User managedUser = userRepository.save(user);
        managedUser.setWallet(walletService.create(new WalletForm(login)));

        return managedUser;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isLoginExists(String login) {
        return userRepository.countByLogin(login) > 0;
    }
}
