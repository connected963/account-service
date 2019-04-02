package com.connected.accountservice.application.service.movement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MovementServiceInjectorTest {

    @Test
    void mustGetMovementServiceInstance() {
        final var movementService = MovementServiceInjector.inject();

        Assertions.assertThat(movementService).isNotNull();
    }

}