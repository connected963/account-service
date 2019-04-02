package com.connected.accountservice.application.service.movement;

import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.infrastructure.repository.movement.MovementRepository;

import java.util.Optional;
import java.util.UUID;

public class MovementServiceInjector {

    private MovementServiceInjector() {

    }

    public static MovementService inject() {
        return MovementServiceInjector.MovementServiceInstance.MOVEMENT_SERVICE_INSTANCE;
    }

    private static class MovementServiceInstance {
        //TODO replace with some implementation
        private static final MovementService MOVEMENT_SERVICE_INSTANCE =
                new MovementService(new MovementRepository() {
                    @Override
                    public void save(Movement movement) {

                    }

                    @Override
                    public Optional<Movement> findMovementById(UUID movementId) {
                        return Optional.empty();
                    }
                });
    }

}
