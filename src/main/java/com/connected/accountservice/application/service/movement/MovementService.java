package com.connected.accountservice.application.service.movement;

import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.validator.movement.MovementPersistValidator;
import com.connected.accountservice.infrastructure.repository.movement.MovementRepository;

import java.util.UUID;

public class MovementService {

    private static final String MOVEMENT_NOT_FOUND = "Movement not found";

    private final MovementRepository movementRepository;

    MovementService(final MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public void save(final Movement movement) {
        final var validator = new MovementPersistValidator();
        validator.validate(movement);

        movementRepository.save(movement);
    }

    public Movement findMovementById(final UUID movementId) {
        return movementRepository.findMovementById(movementId)
                .orElseThrow(() -> new BusinessException(MOVEMENT_NOT_FOUND));
    }


}
