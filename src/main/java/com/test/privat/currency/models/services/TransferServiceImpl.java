package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.TransferForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferServiceImpl implements TransferService {

    private final WalletService walletService;

    @Autowired
    public TransferServiceImpl(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    @Transactional
    public void transferMoney(TransferForm transferWrapper) {
        transferWrapper.getSourceWalletKey();
    }
}
