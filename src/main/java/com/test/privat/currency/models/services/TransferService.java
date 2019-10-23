package com.test.privat.currency.models.services;

import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.exceptions.InsufficientFundsException;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public interface TransferService {
    void transferMoney(Wallet source, Wallet destination, BigDecimal amount) throws RatesNotReceivedException, NoSuchCurrencyRate, InsufficientFundsException;
}
