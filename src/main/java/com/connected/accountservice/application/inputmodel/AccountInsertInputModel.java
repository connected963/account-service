package com.connected.accountservice.application.inputmodel;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class AccountInsertInputModel {

    private final BigDecimal overdraft;

    AccountInsertInputModel(final BigDecimal overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInsertInputModel that = (AccountInsertInputModel) o;
        return Objects.equals(overdraft, that.overdraft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(overdraft);
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccountInsertInputModel.class.getSimpleName() + "[", "]")
                .add("overdraft=" + overdraft)
                .toString();
    }
}
