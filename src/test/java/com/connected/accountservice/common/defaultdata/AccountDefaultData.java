package com.connected.accountservice.common.defaultdata;

import com.connected.accountservice.common.BigDecimalScale;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountDefaultData {

    public static final UUID id = UUID.randomUUID();

    public static final BigDecimal balance = BigDecimalScale.ONE;

    public static final BigDecimal overdraft = BigDecimalScale.ZERO;

    private AccountDefaultData() {
    }
}
