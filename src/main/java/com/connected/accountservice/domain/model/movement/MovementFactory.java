package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.domain.enums.MovementType;

import java.math.BigDecimal;
import java.util.UUID;

public class MovementFactory {

    private MovementFactory() {

    }

    public static Movement createNewMovement(final BigDecimal amount,
                                             final UUID accountId,
                                             final MovementType movementType) {
        final var movementId = UUID.randomUUID();
        return new Movement(movementId, amount, accountId, movementType, Boolean.FALSE);
    }

}
