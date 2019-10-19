package com.test.privat.currency.models.dtolayer.converter;

import com.test.privat.currency.models.dtolayer.wrappers.WalletDetailsWrapper;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WalletDTOConverter implements DTOConverter<Wallet, WalletDetailsWrapper>{

    private final WalletService walletService;

    @Autowired
    public WalletDTOConverter(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public Wallet forward(WalletDetailsWrapper dto) {
        return walletService.getByKey(dto.getKey());
    }

    @Override
    public WalletDetailsWrapper backward(Wallet entity) {
        WalletDetailsWrapper walletDetailsWrapper = new WalletDetailsWrapper();

        walletDetailsWrapper.setBalance(entity.getBalance().toPlainString());
        walletDetailsWrapper.setBlock(entity.isBlock());
        walletDetailsWrapper.setKey(entity.getKey());

        return walletDetailsWrapper;
    }
}
