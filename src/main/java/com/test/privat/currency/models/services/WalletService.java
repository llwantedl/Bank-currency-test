package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.WalletForm;
import com.test.privat.currency.models.entities.Wallet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {
    Wallet create(WalletForm walletForm) throws UsernameNotFoundException;
}
