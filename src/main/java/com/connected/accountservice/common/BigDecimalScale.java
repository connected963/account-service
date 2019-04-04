package com.connected.accountservice.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalScale {

    public static final int SCALE = 5;

    public static final BigDecimal ZERO = BigDecimalScale.valueOf(0);

    public static final BigDecimal ONE = BigDecimalScale.valueOf(1);

    public static final BigDecimal TWO = BigDecimalScale.valueOf(2);

    private BigDecimalScale() {

    }

    public static BigDecimal valueOf(long value) {
        return BigDecimal.valueOf(value)
                .setScale(SCALE, RoundingMode.HALF_UP);
    }

}
