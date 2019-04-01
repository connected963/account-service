package com.connected.accountservice.domain.model.account;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class Account {

    private final UUID id;

    private final BigDecimal balance;

    private final BigDecimal overdraft;

    Account(final UUID id, final BigDecimal balance,
                    final BigDecimal overdraft) {
        this.id = id;
        this.balance = balance;
        this.overdraft = overdraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(overdraft, account.overdraft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, overdraft);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("balance=" + balance)
                .add("overdraft=" + overdraft)
                .toString();
    }
}
