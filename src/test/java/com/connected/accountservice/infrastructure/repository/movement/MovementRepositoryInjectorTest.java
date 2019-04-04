package com.connected.accountservice.infrastructure.repository.movement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MovementRepositoryInjectorTest {

    @Test
    void mustGetMovementRepositoryInstance() {
        final var movementRepository = MovementRepositoryInjector.inject();

        Assertions.assertThat(movementRepository).isNotNull();
    }

}