package com.connected.accountservice.application.inputmodel;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class MoneyTransferInputModel {

    private final UUID accountIdFrom;

    private final UUID accountIdTo;

    private final BigDecimal amount;

    public MoneyTransferInputModel(final UUID accountIdFrom,
                                   final UUID accountIdTo,
                                   final BigDecimal amount) {
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyTransferInputModel that = (MoneyTransferInputModel) o;
        return Objects.equals(accountIdFrom, that.accountIdFrom) &&
                Objects.equals(accountIdTo, that.accountIdTo) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIdFrom, accountIdTo, amount);
    }

    public UUID getAccountIdFrom() {
        return accountIdFrom;
    }

    public UUID getAccountIdTo() {
        return accountIdTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MoneyTransferInputModel.class.getSimpleName() + "[", "]")
                .add("accountIdFrom=" + accountIdFrom)
                .add("accountIdTo=" + accountIdTo)
                .add("amount=" + amount)
                .toString();
    }
}
