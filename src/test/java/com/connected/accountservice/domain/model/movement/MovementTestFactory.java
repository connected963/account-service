package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.common.defaultdata.MovementDefaultData;
import com.connected.accountservice.domain.enums.MovementType;

public class MovementTestFactory {

    private MovementTestFactory() {

    }

    public static Movement createAnDefault() {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementDefaultData.movementType)
                .withStatus(MovementDefaultData.status)
                .build();
    }

    public static Movement createAnDefaultOutput() {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementType.OUTPUT)
                .withStatus(MovementDefaultData.status)
                .build();
    }

}
