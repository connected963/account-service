package com.connected.accountservice.application.service.movement;

import com.connected.accountservice.infrastructure.repository.movement.MovementRepositoryInjector;

public class MovementServiceInjector {

    private MovementServiceInjector() {

    }

    public static MovementService inject() {
        return MovementServiceInjector.MovementServiceInstance.MOVEMENT_SERVICE_INSTANCE;
    }

    private static class MovementServiceInstance {
        private static final MovementService MOVEMENT_SERVICE_INSTANCE =
                createInstance();

        private static MovementService createInstance() {
            final var movementRepository = MovementRepositoryInjector.inject();

            return new MovementService(movementRepository);
        }
    }

}
