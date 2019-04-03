package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.domain.enums.MovementStatus;
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

    private final MovementStatus status;

    Movement(final UUID id, final BigDecimal amount,
             final UUID accountId, final MovementType movementType,
             final MovementStatus status) {
        this.id = id;
        this.amount = amount;
        this.accountId = accountId;
        this.movementType = movementType;
        this.status = status;
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
                status == movement.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, accountId, movementType, status);
    }

        public BigDecimal getComputedValue() {
            return movementType.computeValue(amount);
        }

    public Movement complete() {
        return withStatus(MovementStatus.COMPLETED);
    }

    public Movement abort() {
        return withStatus(MovementStatus.ABORTED);
    }

    private Movement withStatus(final MovementStatus newStatus) {
        return new Movement(id, amount, accountId, getMovementType(), newStatus);
    }

    public Boolean isFinalized() {
        return status == MovementStatus.COMPLETED ||
                status == MovementStatus.ABORTED;
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

    public MovementStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Movement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("amount=" + amount)
                .add("accountId=" + accountId)
                .add("movementType=" + movementType)
                .add("status=" + status)
                .toString();
    }
}
