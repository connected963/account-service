package com.connected.accountservice.domain.model.account;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountFactory {

    private AccountFactory() {

    }

    public static Account createNewAccount(final BigDecimal balance,
                                           final BigDecimal overdraft) {
        final var accountId = UUID.randomUUID();
        return new Account(accountId, balance, overdraft);
    }
}
