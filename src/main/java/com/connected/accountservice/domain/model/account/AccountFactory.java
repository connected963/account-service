package com.connected.accountservice.domain.model.account;

import com.connected.accountservice.common.BigDecimalScale;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountFactory {

    private AccountFactory() {

    }

    public static Account createNewAccount(final BigDecimal overdraft) {
        final var id = UUID.randomUUID();
        return new Account(id, BigDecimalScale.ZERO, overdraft);
    }

    public static Account createExistentAccount(final UUID id,
                                                final BigDecimal balance,
                                                final BigDecimal overdraft) {
        return new Account(id, balance, overdraft);
    }
}
