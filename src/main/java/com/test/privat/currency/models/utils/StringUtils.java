package com.test.privat.currency.models.utils;

import java.math.BigDecimal;

public class StringUtils {
    public static String getPercentage(BigDecimal digit) {
        return digit.multiply(BigDecimal.valueOf(100)).toPlainString() + "%";
    }
}
