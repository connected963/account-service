package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.domain.enums.MovementType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class Movement {

    private final UUID id;

    private final BigDecimal amount;

    private final UUID accountId;

    private final MovementType movementType;

    private final Boolean aborted;

    Movement(final UUID id, final BigDecimal amount,
                    final UUID accountId, final MovementType movementType,
                    final Boolean aborted) {
        this.id = id;
        this.amount = amount;
        this.accountId = accountId;
        this.movementType = movementType;
        this.aborted = aborted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return Objects.equals(id, movement.id) &&
                Objects.equals(amount, movement.amount) &&
                Objects.equals(accountId, movement.accountId) &&
                movementType == movement.movementType &&
                Objects.equals(aborted, movement.aborted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, accountId, movementType, aborted);
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public Boolean getAborted() {
        return aborted;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Movement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("amount=" + amount)
                .add("accountId=" + accountId)
                .add("movementType=" + movementType)
                .add("aborted=" + aborted)
                .toString();
    }
}
