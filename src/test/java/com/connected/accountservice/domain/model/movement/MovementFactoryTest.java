package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.common.defaultdata.MovementDefaultData;
import com.connected.accountservice.domain.enums.MovementType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MovementFactoryTest {

    @Test
    void givenMovementData_mustCreateNewOutputMovementEquivalent() {
        final var movementCreated = MovementFactory.createNewOutputMovement(
                MovementDefaultData.amount, MovementDefaultData.accountId);
        final var movementId = movementCreated.getId();

        final var movementExpected = new MovementBuilder()
                .withId(movementId)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementType.OUTPUT)
                .withStatus(MovementDefaultData.status)
                .build();

        Assertions.assertThat(movementCreated).isEqualTo(movementExpected);
        Assertions.assertThat(movementId).isNotNull();
    }

    @Test
    void givenMovementData_mustCreateNewInputMovementEquivalent() {
        final var movementCreated = MovementFactory.createNewInputMovement(
                MovementDefaultData.amount, MovementDefaultData.accountId);
        final var movementId = movementCreated.getId();

        final var movementExpected = new MovementBuilder()
                .withId(movementId)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementType.INPUT)
                .withStatus(MovementDefaultData.status)
                .build();

        Assertions.assertThat(movementCreated).isEqualTo(movementExpected);
        Assertions.assertThat(movementId).isNotNull();
    }

    @Test
    void givenMovementData_mustCreateExistentMovementEquivalent() {
        final var movementCreated = MovementFactory.createExistentMovement(
                MovementDefaultData.id, MovementDefaultData.amount, MovementDefaultData.accountId,
                MovementDefaultData.movementType, MovementDefaultData.status);

        final var movementExpected = new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementDefaultData.movementType)
                .withStatus(MovementDefaultData.status)
                .build();

        Assertions.assertThat(movementCreated).isEqualTo(movementExpected);
    }

}