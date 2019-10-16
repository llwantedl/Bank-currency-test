package com.test.privat.currency.models.services;

import com.test.privat.currency.models.entities.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getAdministratorRole();
    Role getUserRole();
}
