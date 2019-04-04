package com.connected.accountservice.infrastructure.repository.movement;

import com.connected.accountservice.infrastructure.database.jdbi.JdbiInjector;

public class MovementRepositoryInjector {

    private MovementRepositoryInjector() {

    }

    public static MovementRepository inject() {
        return MovementRepositoryInstance.MOVEMENT_REPOSITORY_INSTANCE;
    }

    private static class MovementRepositoryInstance {
        private static final MovementRepository MOVEMENT_REPOSITORY_INSTANCE =
                createInstance();

        private static MovementRepository createInstance() {
            final var jdbi = JdbiInjector.inject();
            return jdbi.onDemand(MovementRepository.class);
        }
    }
}
