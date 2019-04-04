package com.connected.accountservice.common;

import java.math.BigDecimal;

public class BigDecimalScale {

    public static final int SCALE = 5;

    public static final BigDecimal ZERO = BigDecimal.valueOf(0, SCALE);

    public static final BigDecimal ONE = BigDecimal.valueOf(1, SCALE);

    public static final BigDecimal TWO = BigDecimal.valueOf(2, SCALE);

    private BigDecimalScale() {

    }

    public static BigDecimal valueOf(long value) {
        return BigDecimal.valueOf(value, SCALE);
    }

}
