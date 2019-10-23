package com.test.privat.currency.models.services.system;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface ConfigurationService {
    BigDecimal getCommissionPercentage();
    BigDecimal getCommissionValue();
}
