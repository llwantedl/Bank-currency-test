package com.test.privat.currency.models.dtolayer.converter;

import com.test.privat.currency.models.dtolayer.wrappers.CurrencyWrapper;
import com.test.privat.currency.models.entities.Currency;
import com.test.privat.currency.models.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDTOConverter implements DTOConverter<Currency, CurrencyWrapper> {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyDTOConverter(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public Currency forward(CurrencyWrapper dto) {
        return currencyService.getByKey(dto.getKey());
    }

    @Override
    public CurrencyWrapper backward(Currency entity) {
        return new CurrencyWrapper(entity.getKey());
    }
}
