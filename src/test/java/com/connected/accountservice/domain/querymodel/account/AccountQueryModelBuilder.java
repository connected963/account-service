package com.connected.accountservice.domain.querymodel.account;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountQueryModelBuilder {

    private UUID id;

    private BigDecimal balance;

    public AccountQueryModelBuilder() {

    }

    public AccountQueryModelBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public AccountQueryModelBuilder withBalance(final BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public AccountQueryModel build() {
        return new AccountQueryModel(id, balance);
    }
}
