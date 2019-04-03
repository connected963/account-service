package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.domain.enums.MovementStatus;
import com.connected.accountservice.domain.enums.MovementType;

import java.math.BigDecimal;
import java.util.UUID;

public class MovementFactory {

    private MovementFactory() {

    }

    public static Movement createNewOutputMovement(final BigDecimal amount,
                                                   final UUID accountId) {
        final var movementId = UUID.randomUUID();
        return new Movement(movementId, amount, accountId,
                MovementType.OUTPUT, MovementStatus.PROCESSING);
    }

    public static Movement createNewInputMovement(final BigDecimal amount,
                                                  final UUID accountId) {
        final var movementId = UUID.randomUUID();
        return new Movement(movementId, amount, accountId,
                MovementType.INPUT, MovementStatus.PROCESSING);
    }

}
