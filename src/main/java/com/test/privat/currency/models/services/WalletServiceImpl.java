package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.WalletForm;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.exceptions.InsufficientFundsException;
import com.test.privat.currency.models.repositories.UserRepository;
import com.test.privat.currency.models.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setBlock(false);
        wallet.setKey(UUID.randomUUID().toString());

        return walletRepository.save(wallet);
    }

    @Override
    public List<Wallet> getByUser(User user) {
        return walletRepository.getByUser(user);
    }

    @Override
    public Wallet getByKey(String key) {
        return walletRepository.getByKey(key);
    }

    @Override
    public void addToBalance(Wallet wallet, BigDecimal amount) {
        BigDecimal currentBalance = wallet.getBalance();
        wallet.setBalance(currentBalance.add(amount));
        walletRepository.save(wallet);
    }

    @Override
    public void subtractFromBalance(Wallet wallet, BigDecimal amount) throws InsufficientFundsException {
        BigDecimal currentBalance = wallet.getBalance();

        if(currentBalance.compareTo(amount) < 0){
            throw new InsufficientFundsException();
        }

        wallet.setBalance(currentBalance.subtract(amount));
        walletRepository.save(wallet);
    }
}
