package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.TransferForm;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.exceptions.InsufficientFundsException;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferInitializationServiceImpl implements TransferInitializationService {

    private final TransferService transferService;

    private final WalletService walletService;

    @Autowired
    public TransferInitializationServiceImpl(TransferService transferService,
                                             WalletService walletService) {
        this.transferService = transferService;
        this.walletService = walletService;
    }

    @Override
    public void initializeTransfer(TransferForm transferForm)
            throws InsufficientFundsException, RatesNotReceivedException, NoSuchCurrencyRate {
        Wallet sourceWallet = walletService.getByKey(transferForm.getSourceWalletKey());
        Wallet destinationWallet = walletService.getByKey(transferForm.getDestinationWalletKey());

        transferService.transferMoney(sourceWallet, destinationWallet, transferForm.getAmount());
    }
}
