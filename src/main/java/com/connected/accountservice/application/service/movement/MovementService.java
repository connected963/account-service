package com.connected.accountservice.application.service.movement;

import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.validator.movement.MovementPersistValidator;
import com.connected.accountservice.infrastructure.repository.movement.MovementRepository;

import java.util.Objects;
import java.util.UUID;

public class MovementService {

    private static final String MOVEMENT_NULL = "Movement cannot be null";
    private static final String MOVEMENT_NOT_FOUND = "Movement not found";

    private final MovementRepository movementRepository;

    MovementService(final MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public void insert(final Movement movement) {
        Objects.requireNonNull(movement, MOVEMENT_NULL);

        validate(movement);

        movementRepository.insert(movement);
    }

    private void validate(final Movement movement) {
        final var validator = new MovementPersistValidator();
        validator.validate(movement);
    }

    public void update(final Movement movement) {
        Objects.requireNonNull(movement, MOVEMENT_NULL);

        validate(movement);

        movementRepository.update(movement);
    }

    public Movement findMovementById(final UUID movementId) {
        return movementRepository.findMovementById(movementId)
                .orElseThrow(() -> new BusinessException(MOVEMENT_NOT_FOUND));
    }


}
