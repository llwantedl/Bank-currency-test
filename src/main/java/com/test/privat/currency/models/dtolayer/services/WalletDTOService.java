package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.wrappers.WalletDetailsWrapper;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletDTOService extends DTOService<Wallet, WalletDetailsWrapper>{
    List<WalletDetailsWrapper> getWalletDetails(User user);
    WalletDetailsWrapper getWalletDetails(Wallet wallet);
}
