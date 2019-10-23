package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.TransferForm;
import com.test.privat.currency.models.exceptions.InsufficientFundsException;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import org.springframework.stereotype.Service;

@Service
public interface TransferInitializationService {
    void initializeTransfer(TransferForm transferForm)
            throws InsufficientFundsException, RatesNotReceivedException, NoSuchCurrencyRate;
}
