package com.connected.accountservice.infrastructure.repository.movement;

import com.connected.accountservice.domain.model.movement.Movement;

import java.util.Optional;
import java.util.UUID;

public interface MovementRepository {

    void save(final Movement movement);

    Optional<Movement> findMovementById(final UUID movementId);

}
