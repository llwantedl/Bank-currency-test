package com.test.privat.currency.models.services.privatrates;

import com.test.privat.currency.models.dtolayer.wrappers.ExchangeRatesWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.RateWrapper;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import org.springframework.stereotype.Service;

@Service
public interface RateAccessorService {
    ExchangeRatesWrapper getRates() throws RatesNotReceivedException;
    RateWrapper getCurrencyRate(ExchangeRatesWrapper exchangeRatesWrapper, String currencyKey) throws NoSuchCurrencyRate;
}
