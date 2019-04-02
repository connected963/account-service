package com.connected.accountservice.common.defaultdata;

import com.connected.accountservice.domain.enums.MovementStatus;
import com.connected.accountservice.domain.enums.MovementType;

import java.math.BigDecimal;
import java.util.UUID;

public class MovementDefaultData {

    public static final UUID id = UUID.randomUUID();

    public static final BigDecimal amount = BigDecimal.ONE;

    public static final UUID accountId = AccountDefaultData.id;

    public static final MovementType movementType = MovementType.INPUT;

    public static final MovementStatus status = MovementStatus.AWAITING_PROCESSING;

    private MovementDefaultData() {
    }
}
