package com.test.privat.currency.models.services;

import com.test.privat.currency.models.entities.Currency;
import org.springframework.stereotype.Service;

@Service
public interface CurrencyService {
    boolean isExistsByKey(String key);
    Currency getByKey(String key);
}
