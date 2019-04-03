package com.connected.accountservice.domain.model.movement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MovementTest {

    @Test
    void givenInputMovement_mustGetAPositiveComputedValue() {
        final var inputMovement = MovementTestFactory.createAnDefault();

        final var computedValue = inputMovement.getComputedValue();

        Assertions.assertThat(computedValue).isPositive();
        Assertions.assertThat(computedValue).isEqualTo(inputMovement.getAmount());
    }

    @Test
    void givenOutputMovement_mustGetANegativeComputedValue() {
        final var outputMovement = MovementTestFactory.createAnDefaultOutput();

        final var computedValue = outputMovement.getComputedValue();

        Assertions.assertThat(computedValue).isNegative();
        Assertions.assertThat(computedValue).isEqualTo(
                outputMovement.getAmount().negate());
    }
}