package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.WalletForm;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface WalletService {
    Wallet create(WalletForm walletForm) throws UsernameNotFoundException;
    List<Wallet> getByUser(User user);
    Wallet getByKey(String key);
    void addToBalance(Wallet wallet, BigDecimal amount);
    void subtractFromBalance(Wallet wallet, BigDecimal amount);
}
