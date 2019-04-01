package com.connected.accountservice.domain.model.account;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountBuilder {

    private UUID id;

    private BigDecimal balance;

    private BigDecimal overdraft;

    public AccountBuilder() {
    }

    public AccountBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public AccountBuilder withBalance(final BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public AccountBuilder withOverdraft(final BigDecimal overdraft) {
        this.overdraft = overdraft;
        return this;
    }

    public Account build() {
        return new Account(id, balance, overdraft);
    }
}
