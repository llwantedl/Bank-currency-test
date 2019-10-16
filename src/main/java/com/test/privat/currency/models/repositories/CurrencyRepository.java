package com.test.privat.currency.models.repositories;

import com.test.privat.currency.models.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency getByKey(String key);
    Long countByKey(String key);
}
