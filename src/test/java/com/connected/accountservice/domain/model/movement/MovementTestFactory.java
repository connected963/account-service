package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.common.defaultdata.MovementDefaultData;
import com.connected.accountservice.domain.enums.MovementStatus;
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

    public static Movement createAnDefaultCompleted() {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementType.INPUT)
                .withStatus(MovementStatus.COMPLETED)
                .build();
    }

    public static Movement createAnDefaultAborted() {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementType.INPUT)
                .withStatus(MovementStatus.ABORTED)
                .build();
    }

    public static Movement createAnDefaultOutputAborted() {
        return new MovementBuilder()
                .withId(MovementDefaultData.id)
                .withAmount(MovementDefaultData.amount)
                .withAccountId(MovementDefaultData.accountId)
                .withMovementType(MovementType.OUTPUT)
                .withStatus(MovementStatus.ABORTED)
                .build();
    }

}
