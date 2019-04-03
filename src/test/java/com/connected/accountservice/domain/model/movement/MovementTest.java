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

    @Test
    void givenMovement_mustCompleteMovement() {
        final var movement = MovementTestFactory.createAnDefault();

        final var movementFinalized = movement.complete();

        final var movementExpected = MovementTestFactory.createAnDefaultCompleted();

        Assertions.assertThat(movementFinalized).isEqualTo(movementExpected);
    }

    @Test
    void givenMovement_mustAbortMovement() {
        final var movement = MovementTestFactory.createAnDefault();

        final var movementFinalized = movement.abort();

        final var movementExpected = MovementTestFactory.createAnDefaultAborted();

        Assertions.assertThat(movementFinalized).isEqualTo(movementExpected);
    }

    @Test
    void givenProcessingMovement_mustConsiderUnfinalized() {
        final var unfinalizedMovement = MovementTestFactory.createAnDefault();

        final var isFinalized = unfinalizedMovement.isFinalized();

        Assertions.assertThat(isFinalized).isFalse();
    }

    @Test
    void givenCompletedMovement_mustConsiderFinalized() {
        final var completedMovement = MovementTestFactory.createAnDefaultCompleted();

        final var isFinalized = completedMovement.isFinalized();

        Assertions.assertThat(isFinalized).isTrue();
    }

    @Test
    void givenAbortedMovement_mustConsiderFinalized() {
        final var completedMovement = MovementTestFactory.createAnDefaultAborted();

        final var isFinalized = completedMovement.isFinalized();

        Assertions.assertThat(isFinalized).isTrue();
    }
}