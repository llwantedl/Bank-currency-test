package com.test.privat.currency.models.repositories;

import com.test.privat.currency.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role getByKey(String key);
}
