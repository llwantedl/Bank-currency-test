package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.wrappers.CurrencyWrapper;
import com.test.privat.currency.models.entities.Currency;

import java.util.List;

public interface CurrencyDTOService extends DTOService<Currency, CurrencyWrapper> {
    List<CurrencyWrapper> getAll();
}
