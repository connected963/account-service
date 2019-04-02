package com.connected.accountservice.domain.enums;

import java.util.function.DoubleFunction;

public enum MovementType {
    INPUT(inputValue -> inputValue),
    OUTPUT(outputValue -> outputValue * -1);

    private final DoubleFunction<Double> compute;

    MovementType(final DoubleFunction<Double> compute) {
        this.compute = compute;
    }

    public Double computeValue(final Double value) {
        return compute.apply(value);
    }
}
