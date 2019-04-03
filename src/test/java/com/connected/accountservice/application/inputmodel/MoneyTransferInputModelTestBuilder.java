package com.connected.accountservice.application.inputmodel;

import java.math.BigDecimal;
import java.util.UUID;

public class MoneyTransferInputModelTestBuilder {

    private UUID accountIdFrom;

    private UUID accountIdTo;

    private BigDecimal amount;

    public MoneyTransferInputModelTestBuilder() {
    }

    public MoneyTransferInputModelTestBuilder withAccountIdFrom(final UUID accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
        return this;
    }

    public MoneyTransferInputModelTestBuilder withAccountIdTo(final UUID accountIdTo) {
        this.accountIdTo = accountIdTo;
        return this;
    }

    public MoneyTransferInputModelTestBuilder withAmount(final BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public MoneyTransferInputModel build() {
        return new MoneyTransferInputModel(accountIdFrom, accountIdTo, amount);
    }

}
