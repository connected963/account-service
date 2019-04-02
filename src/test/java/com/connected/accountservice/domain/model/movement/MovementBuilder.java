package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.domain.enums.MovementStatus;
import com.connected.accountservice.domain.enums.MovementType;

import java.math.BigDecimal;
import java.util.UUID;

public class MovementBuilder {

    private UUID id;

    private BigDecimal amount;

    private UUID accountId;

    private MovementType movementType;

    private MovementStatus status;

    public MovementBuilder() {
    }

    public MovementBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    public MovementBuilder withAmount(final BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public MovementBuilder withAccountId(final UUID accountId) {
        this.accountId = accountId;
        return this;
    }

    public MovementBuilder withMovementType(final MovementType movementType) {
        this.movementType = movementType;
        return this;
    }

    public MovementBuilder withStatus(final MovementStatus status) {
        this.status = status;
        return this;
    }

    public Movement build() {
        return new Movement(id, amount, accountId, movementType, status);
    }
}
