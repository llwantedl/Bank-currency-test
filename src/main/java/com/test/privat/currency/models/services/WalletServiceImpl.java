package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.WalletForm;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.repositories.UserRepository;
import com.test.privat.currency.models.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final UserRepository userRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository,
                             UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Wallet create(WalletForm walletForm) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(walletForm.getOwnerUsername());

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Error while creating wallet! " +
                    "Given username not found in the database!");
        }

        Wallet wallet = new Wallet();

        wallet.setUser(user);
        wallet.setMoney(BigDecimal.ZERO);
        wallet.setBlock(false);
        wallet.setKey(UUID.randomUUID().toString());

        return walletRepository.save(wallet);
    }
}
