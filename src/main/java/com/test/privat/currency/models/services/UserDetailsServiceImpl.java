package com.test.privat.currency.models.services;

import com.test.privat.currency.models.BankUserDetails;
import com.test.privat.currency.models.entities.Role;
import com.test.privat.currency.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getByLogin(login);

        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("Username not found");
        }

        BankUserDetails userDetails = new BankUserDetails(user);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : userDetails.getUser().getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getKey()));
        }
        return userDetails;
    }
}
