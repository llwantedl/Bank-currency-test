package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.wrappers.CurrencyWrapper;
import com.test.privat.currency.models.dtolayer.converter.Converter;
import com.test.privat.currency.models.entities.Currency;
import com.test.privat.currency.models.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyDTOServiceImpl implements CurrencyDTOService {

    private final Converter<Currency, CurrencyWrapper> converter;
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyDTOServiceImpl(Converter<Currency, CurrencyWrapper> converter,
                                  CurrencyService currencyService) {
        this.converter = converter;
        this.currencyService = currencyService;
    }

    @Override
    public List<CurrencyWrapper> convertToDTO(List<Currency> entities) {
        return entities
                .stream()
                .map(converter::backward)
                .collect(Collectors.toList());
    }

    @Override
    public List<CurrencyWrapper> getAll() {
        return convertToDTO(currencyService.getAll());
    }
}
