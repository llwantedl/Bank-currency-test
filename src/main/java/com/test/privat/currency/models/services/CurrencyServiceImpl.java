package com.test.privat.currency.models.services;

import com.test.privat.currency.models.entities.Currency;
import com.test.privat.currency.models.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean isExistsByKey(String key) {
        return currencyRepository.countByKey(key) > 0;
    }

    @Override
    public Currency getByKey(String key) {
        return currencyRepository.getByKey(key);
    }

    @Override
    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }
}
