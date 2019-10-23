package com.test.privat.currency.models.services.privatrates;

import com.test.privat.currency.models.dtolayer.wrappers.ExchangeRatesWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.RateWrapper;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import com.test.privat.currency.models.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

@Service
public class RateAccessorServiceImpl implements RateAccessorService {

    private static final String RATES_URL = "https://api.privatbank.ua/p24api/exchange_rates?date=";

    private final Logger logger = LoggerFactory.getLogger(RateAccessorServiceImpl.class);

    @Override
    public ExchangeRatesWrapper getRates() throws RatesNotReceivedException {
        try {
            URL url = new URL(RATES_URL + DateUtils.getYesterdayStringDate());
            JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRatesWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (ExchangeRatesWrapper) unmarshaller.unmarshal(url);
        } catch (Exception e) {
            logger.error("Rates not received error! " + e.getMessage());
            throw new RatesNotReceivedException("Rates not received", e);
        }
    }

    @Override
    public RateWrapper getCurrencyRate(ExchangeRatesWrapper exchangeRatesWrapper, String currencyKey) throws NoSuchCurrencyRate {
        for (RateWrapper rateWrapper : exchangeRatesWrapper.getRates()) {
            if (currencyKey.equals(rateWrapper.getCurrency())) {
                return rateWrapper;
            }
        }

        throw new NoSuchCurrencyRate();
    }
}
