package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.common.defaultdata.MovementDefaultData;

public class MovementTestFactory {

    private MovementTestFactory() {

    }

    public static Movement createAnDefault() {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementDefaultData.movementType)
                .withAborted(MovementDefaultData.aborted)
                .build();
    }

}
