package com.test.privat.currency.models.services;

import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.exceptions.InsufficientFundsException;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import com.test.privat.currency.models.services.privatrates.CurrencyRateService;
import com.test.privat.currency.models.services.system.ConfigurationService;
import com.test.privat.currency.models.wrappers.OperationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    private final WalletService walletService;

    private final CurrencyRateService currencyRateService;

    private final ConfigurationService configurationService;

    private final OperationService operationService;

    @Autowired
    public TransferServiceImpl(WalletService walletService,
                               CurrencyRateService currencyRateService,
                               ConfigurationService configurationService,
                               OperationService operationService) {
        this.walletService = walletService;
        this.currencyRateService = currencyRateService;
        this.configurationService = configurationService;
        this.operationService = operationService;
    }


    @Override
    public void transferMoney(Wallet source, Wallet destination, BigDecimal amount)
            throws RatesNotReceivedException, NoSuchCurrencyRate, InsufficientFundsException {
        BigDecimal commissionPercentage = configurationService.getCommissionPercentage();
        BigDecimal commissionValue = configurationService.getCommissionValue();
        BigDecimal transferRate = currencyRateService.getTransferRateBetweenCurrencies(
                source.getUser().getValueCurrency(),
                destination.getUser().getValueCurrency());
        BigDecimal commission = amount.multiply(commissionPercentage).add(commissionValue);
        BigDecimal receiveValue = amount.multiply(transferRate);

        walletService.subtractFromBalance(source, amount.add(commission));
        walletService.addToBalance(destination, receiveValue);

        createOperation(source, destination, receiveValue, transferRate, commission);
    }

    private void createOperation(Wallet source, Wallet destination, BigDecimal amount,
                                 BigDecimal transferRate, BigDecimal commission) {
        OperationWrapper operationWrapper = new OperationWrapper();

        operationWrapper.setCommission(commission);
        operationWrapper.setAmount(amount);
        operationWrapper.setDestinationWallet(destination);
        operationWrapper.setSourceWallet(source);
        operationWrapper.setRate(transferRate);

        operationService.create(operationWrapper);
    }
}
