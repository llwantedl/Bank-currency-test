package com.test.privat.currency.models.services.system;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final BigDecimal PERCENTAGE_COMMISSION = BigDecimal.valueOf(0.02);
    private static final BigDecimal VALUE_COMMISSION = BigDecimal.ZERO;

    @Override
    public BigDecimal getCommissionPercentage() {
        return PERCENTAGE_COMMISSION;
    }

    @Override
    public BigDecimal getCommissionValue() {
        return VALUE_COMMISSION;
    }
}
