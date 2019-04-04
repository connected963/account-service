package com.connected.accountservice.domain.model.account;

import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.movement.Movement;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class Account {

    private static final String BALANCE_LESS_THAN_AMOUNT_AVAILABLE =
            "New balance cannot be less than amount available";

    private static final String REVERT_MOVEMENT_FINALIZED =
            "Finalized movement cannot be reverted";

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

    public Account revertMovement(final Movement movementToRevert) {
        validateMovementToRevert(movementToRevert);

        final var computedValue = movementToRevert.getComputedValue();
        final var newBalance = balance.add(computedValue.negate());

        return withBalance(newBalance);
    }

    private void validateMovementToRevert(final Movement movementToRevert) {
        if (movementToRevert.isFinalized()) {
            throw new BusinessException(REVERT_MOVEMENT_FINALIZED);
        }
    }

    public Account recalculateBalanceWithMovement(final Movement movement) {
        final var movementComputedValue = movement.getComputedValue();
        final var newBalance = balance.add(movementComputedValue);

        return withBalance(newBalance);
    }

    public Account withBalance(final BigDecimal newBalance) {
        if (newBalanceIsLowerAmountAvailable(newBalance)) {
            throw new BusinessException(BALANCE_LESS_THAN_AMOUNT_AVAILABLE);
        }

        return new Account(id, newBalance, overdraft);
    }

    private Boolean newBalanceIsLowerAmountAvailable(final BigDecimal newBalance) {
        return newBalance.compareTo(overdraft.negate()) < 0;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getOverdraft() {
        return overdraft;
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
