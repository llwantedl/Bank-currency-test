package com.test.privat.currency.models.repositories;

import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{
    List<Wallet> getByUser(User user);
    Wallet getByKey(String key);
}
