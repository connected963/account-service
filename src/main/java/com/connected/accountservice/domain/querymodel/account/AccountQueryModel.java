package com.connected.accountservice.domain.querymodel.account;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class AccountQueryModel {

    private UUID id;

    private BigDecimal balance;

    AccountQueryModel(final UUID id, final BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountQueryModel that = (AccountQueryModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccountQueryModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("balance=" + balance)
                .toString();
    }
}
