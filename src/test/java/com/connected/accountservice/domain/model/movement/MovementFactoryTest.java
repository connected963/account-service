package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.common.defaultdata.MovementDefaultData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MovementFactoryTest {

    @Test
    void givenMovementData_mustCreateNewMovementEquivalent() {
        final var movementCreated = MovementFactory.createNewMovement(
                MovementDefaultData.amount, MovementDefaultData.accountId,
                MovementDefaultData.movementType);
        final var movementId = movementCreated.getId();

        final var movementExpected = new MovementBuilder()
                .withId(movementId)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementDefaultData.movementType)
                .withStatus(MovementDefaultData.status)
                .build();

        Assertions.assertThat(movementCreated).isEqualTo(movementExpected);
        Assertions.assertThat(movementId).isNotNull();
    }

}