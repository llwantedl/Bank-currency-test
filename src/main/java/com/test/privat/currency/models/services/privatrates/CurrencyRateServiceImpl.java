package com.test.privat.currency.models.services.privatrates;

import com.test.privat.currency.models.dtolayer.wrappers.ExchangeRatesWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.RateWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.TransferWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.TransferForm;
import com.test.privat.currency.models.entities.Currency;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import com.test.privat.currency.models.services.WalletService;
import com.test.privat.currency.models.services.system.ConfigurationService;
import com.test.privat.currency.models.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final RateAccessorService rateAccessorService;

    private final WalletService walletService;

    private final ConfigurationService configurationService;

    @Autowired
    public CurrencyRateServiceImpl(RateAccessorService rateAccessorService,
                                   WalletService walletService,
                                   ConfigurationService configurationService) {
        this.rateAccessorService = rateAccessorService;
        this.walletService = walletService;
        this.configurationService = configurationService;
    }

    @Override
    public BigDecimal getTransferRateBetweenCurrencies(Currency from, Currency to) throws RatesNotReceivedException, NoSuchCurrencyRate {
        ExchangeRatesWrapper rates = rateAccessorService.getRates();

        String baseCurrencyLit = rates.getBaseCurrencyLit();
        String fromCurrencyKey = from.getKey();
        String toCurrencyKey = to.getKey();

        RateWrapper fromRate = rateAccessorService.getCurrencyRate(rates, fromCurrencyKey);
        RateWrapper toRate = rateAccessorService.getCurrencyRate(rates, toCurrencyKey);

        if (fromCurrencyKey.equals(toCurrencyKey)) {
            return BigDecimal.ONE;
        }

        if (baseCurrencyLit.equals(fromCurrencyKey)) {
            return BigDecimal.ONE.divide(BigDecimal.valueOf(toRate.getSaleRateNB()), 10, RoundingMode.HALF_EVEN);
        } else if (baseCurrencyLit.equals(toCurrencyKey)) {
            return BigDecimal.valueOf(fromRate.getPurchaseRateNB());
        } else {
            return BigDecimal.valueOf(fromRate.getSaleRateNB() * toRate.getPurchaseRateNB());
        }
    }

    @Override
    public TransferWrapper getDetails(TransferForm transferWrapper) throws RatesNotReceivedException, NoSuchCurrencyRate {
        TransferWrapper transferDetailsWrapper = new TransferWrapper();

        String sourceWalletKey = transferWrapper.getSourceWalletKey();
        String destinationWalletKey = transferWrapper.getDestinationWalletKey();
        BigDecimal amount = transferWrapper.getAmount();

        Wallet sourceWallet = walletService.getByKey(sourceWalletKey);
        Wallet destinationWallet = walletService.getByKey(destinationWalletKey);

        User sourceUser = sourceWallet.getUser();
        User destinationUser = destinationWallet.getUser();

        Currency sourceCurrency = sourceUser.getValueCurrency();
        Currency destinationCurrency = destinationUser.getValueCurrency();

        BigDecimal commissionPercentage = configurationService.getCommissionPercentage();
        BigDecimal commissionValue = configurationService.getCommissionValue();

        String commissionPercentageString = StringUtils.getPercentage(commissionPercentage);
        String commissionValueString = commissionValue.toPlainString() + " " + sourceCurrency.getKey();

        BigDecimal transferRate = getTransferRateBetweenCurrencies(sourceCurrency, destinationCurrency);
        BigDecimal commissionedAmount = amount.add(amount.multiply(commissionPercentage)).add(commissionValue);
        BigDecimal transferMoney = amount.multiply(transferRate);

        transferDetailsWrapper.setTransferRate(transferRate.toPlainString());
        transferDetailsWrapper.setSourcePay(commissionedAmount.toPlainString() + " " + sourceCurrency.getKey());
        transferDetailsWrapper.setDestinationReceive(transferMoney.toPlainString() + " " + destinationCurrency.getKey());
        transferDetailsWrapper.setCommissionPercentage(commissionPercentageString);
        transferDetailsWrapper.setCommissionValue(commissionValueString);

        return transferDetailsWrapper;
    }
}
