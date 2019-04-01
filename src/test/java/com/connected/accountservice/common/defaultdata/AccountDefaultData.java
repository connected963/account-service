package com.connected.accountservice.common.defaultdata;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountDefaultData {

    public static final UUID id = UUID.randomUUID();

    public static final BigDecimal balance = BigDecimal.ZERO;

    public static final BigDecimal overdraft = BigDecimal.ZERO;

    private AccountDefaultData() {
    }
}
