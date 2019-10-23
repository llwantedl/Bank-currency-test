package com.test.privat.currency.models.services.privatrates;

import com.test.privat.currency.models.dtolayer.wrappers.TransferWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.TransferForm;
import com.test.privat.currency.models.entities.Currency;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CurrencyRateService {
    BigDecimal getTransferRateBetweenCurrencies(Currency from, Currency to) throws RatesNotReceivedException, NoSuchCurrencyRate;
    TransferWrapper getDetails(TransferForm transferWrapper) throws RatesNotReceivedException, NoSuchCurrencyRate;
}
