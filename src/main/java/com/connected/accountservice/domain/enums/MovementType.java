package com.connected.accountservice.domain.enums;

import java.math.BigDecimal;
import java.util.function.Function;

public enum MovementType {
    INPUT(inputValue -> inputValue),
    OUTPUT(BigDecimal::negate);

    private final Function<BigDecimal, BigDecimal> compute;

    MovementType(final Function<BigDecimal, BigDecimal> compute) {
        this.compute = compute;
    }

    public BigDecimal computeValue(final BigDecimal value) {
        return compute.apply(value);
    }
}
