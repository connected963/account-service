package com.connected.accountservice.infrastructure.repository.movement;

import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import com.connected.accountservice.infrastructure.repository.BaseRepositoryTest;
import com.connected.accountservice.infrastructure.repository.common.mapper.MovementMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

class MovementRepositoryTest extends BaseRepositoryTest {

    private final MovementRepository movementRepository;

    MovementRepositoryTest() {
        this.movementRepository = MovementRepositoryInjector.inject();
    }

    @Test
    void givenMovement_mustInsertMovement() {
        final var movement = MovementTestFactory.createAnDefault();

        movementRepository.insert(movement);

        final var movementInserted = findMovementById(movement.getId());

        Assertions.assertThat(movementInserted.get()).isEqualTo(movement);
    }

    private Optional<Movement> findMovementById(final UUID movementId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("select * from movement where id = :id")
                        .bind("id", movementId)
                        .map(new MovementMapper())
                        .findFirst());
    }

    @Test
    void givenInsertedMovement_mustUpdateMovement() {
        final var movement = MovementTestFactory.createAnDefault();
        insertMovement(movement);

        final var movementCompleted = movement.complete();

        movementRepository.update(movementCompleted);

        final var movementUpdated = findMovementById(movement.getId());

        Assertions.assertThat(movementUpdated.get()).isEqualTo(movementCompleted);
    }

    private void insertMovement(final Movement movement) {
        jdbi.useHandle(handle ->
                handle.createUpdate(
                        "insert into movement(id, amount, accountId, movementType, status) " +
                                "values(:id, :amount, :accountId, :movementType, :status)")
                        .bindBean(movement)
                        .execute());
    }

    @Test
    void givenMovementId_mustFindMovement() {
        final var movement = MovementTestFactory.createAnDefault();
        insertMovement(movement);

        final var movementFound = movementRepository.findMovementById(movement.getId());

        Assertions.assertThat(movementFound.get()).isEqualTo(movement);
    }

}