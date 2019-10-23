package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.wrappers.CurrencyWrapper;
import com.test.privat.currency.models.dtolayer.converter.DTOConverter;
import com.test.privat.currency.models.entities.Currency;
import com.test.privat.currency.models.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyDTOServiceImpl implements CurrencyDTOService {

    private final DTOConverter<Currency, CurrencyWrapper> DTOConverter;
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyDTOServiceImpl(DTOConverter<Currency, CurrencyWrapper> DTOConverter,
                                  CurrencyService currencyService) {
        this.DTOConverter = DTOConverter;
        this.currencyService = currencyService;
    }

    @Override
    public List<CurrencyWrapper> convertListToDTO(List<Currency> entities) {
        return entities
                .stream()
                .map(DTOConverter::backward)
                .collect(Collectors.toList());
    }

    @Override
    public List<CurrencyWrapper> getAll() {
        return convertListToDTO(currencyService.getAll());
    }
}
