package com.connected.accountservice.application.inputmodel;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class AccountInsertInputModel {

    private final BigDecimal balance;

    private final BigDecimal overdraft;

    AccountInsertInputModel(final BigDecimal balance,
                            final BigDecimal overdraft) {
        this.balance = balance;
        this.overdraft = overdraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInsertInputModel that = (AccountInsertInputModel) o;
        return Objects.equals(balance, that.balance) &&
                Objects.equals(overdraft, that.overdraft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, overdraft);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccountInsertInputModel.class.getSimpleName() + "[", "]")
                .add("balance=" + balance)
                .add("overdraft=" + overdraft)
                .toString();
    }
}
