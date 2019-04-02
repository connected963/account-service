package com.connected.accountservice.application.service.movement;

import com.connected.accountservice.common.defaultdata.MovementDefaultData;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.movement.MovementBuilder;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import com.connected.accountservice.infrastructure.repository.movement.MovementRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MovementServiceTest {

    private final MovementRepository movementRepositoryMock;

    private final MovementService movementService;

    MovementServiceTest() {
        this.movementRepositoryMock = Mockito.mock(MovementRepository.class);
        this.movementService = new MovementService(movementRepositoryMock);
    }

    static List<Movement> invalidMovementProvider() {
        return List.of(
                new MovementBuilder()
                        .withAmount(MovementDefaultData.amount)
                        .withAccountId(MovementDefaultData.accountId)
                        .withMovementType(MovementDefaultData.movementType)
                        .withStatus(MovementDefaultData.status)
                        .build(),
                new MovementBuilder()
                        .withId(MovementDefaultData.id)
                        .withAccountId(MovementDefaultData.accountId)
                        .withMovementType(MovementDefaultData.movementType)
                        .withStatus(MovementDefaultData.status)
                        .build(),
                new MovementBuilder()
                        .withId(MovementDefaultData.id)
                        .withAmount(MovementDefaultData.amount)
                        .withMovementType(MovementDefaultData.movementType)
                        .withStatus(MovementDefaultData.status)
                        .build(),
                new MovementBuilder()
                        .withId(MovementDefaultData.id)
                        .withAmount(MovementDefaultData.amount)
                        .withAccountId(MovementDefaultData.accountId)
                        .withStatus(MovementDefaultData.status)
                        .build(),
                new MovementBuilder()
                        .withId(MovementDefaultData.id)
                        .withAmount(MovementDefaultData.amount)
                        .withAccountId(MovementDefaultData.accountId)
                        .withMovementType(MovementDefaultData.movementType)
                        .build(),
                new MovementBuilder()
                        .withId(MovementDefaultData.id)
                        .withAmount(BigDecimal.valueOf(-1))
                        .withAccountId(MovementDefaultData.accountId)
                        .withMovementType(MovementDefaultData.movementType)
                        .withStatus(MovementDefaultData.status)
                        .build()
        );
    }

    @Test
    void givenValidMovement_mustPersist() {
        final var movement = MovementTestFactory.createAnDefault();

        movementService.save(movement);

        Mockito.verify(movementRepositoryMock).save(movement);
    }

    @ParameterizedTest
    @MethodSource("invalidMovementProvider")
    void givenInvalidMovement_mustFailToPersist(final Movement movement) {
        assertThrows(BusinessException.class, () -> movementService.save(movement));
    }

    @Test
    void givenIdFromExistentMovement_mustFindMovement() {
        final var movementExpected = MovementTestFactory.createAnDefault();
        Mockito.when(movementRepositoryMock.findMovementById(MovementDefaultData.id))
                .thenReturn(Optional.of(movementExpected));

        final var movementFound = movementService.findMovementById(MovementDefaultData.id);

        Assertions.assertThat(movementFound).isEqualTo(movementExpected);
    }

    @Test
    void givenIdFromInexistentMovement_mustFailToFindMovement() {
        Mockito.when(movementRepositoryMock.findMovementById(MovementDefaultData.id))
                .thenReturn(Optional.empty());

        assertThrows(BusinessException.class,
                () -> movementService.findMovementById(MovementDefaultData.id));
    }

}