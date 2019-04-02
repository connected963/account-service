package com.connected.accountservice.domain.validator.movement;

import com.connected.accountservice.domain.enums.MovementStatus;
import com.connected.accountservice.domain.enums.MovementType;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.validator.common.Validator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class MovementPersistValidator implements Validator<Movement> {

    private static final Double AMOUNT_MIN_VALUE = 0.0;

    private static final String MOVEMENT_WITH_ID_NULL = "Id cannot be null";
    private static final String MOVEMENT_WITH_AMOUNT_NULL = "Amount cannot be null";
    private static final String MOVEMENT_WITH_AMOUNT_BELOW_MIN_VALUE =
            String.format("Amount cannot be less than or equals to %f", AMOUNT_MIN_VALUE);
    private static final String MOVEMENT_WITH_ACCOUNT_ID_NULL = "Account id cannot be null";
    private static final String MOVEMENT_WITH_MOVEMENT_TYPE_NULL = "MovementType cannot be null";
    private static final String MOVEMENT_WITH_STATUS_NULL = "Status cannot be null";


    @Override
    public void validate(final Movement movement) {
        validateId(movement.getId());
        validateAmount(movement.getAmount());
        validateAccountId(movement.getAccountId());
        validateMovementType(movement.getMovementType());
        validateStatus(movement.getStatus());
    }

    private void validateId(final UUID movementId) {
        if (Objects.isNull(movementId)) {
            throw new BusinessException(MOVEMENT_WITH_ID_NULL);
        }
    }

    private void validateAmount(final BigDecimal amount) {
        validateAmountNonNull(amount);
        validateAmountMinValue(amount);
    }

    private void validateAmountNonNull(final BigDecimal amount) {
        if (Objects.isNull(amount)) {
            throw new BusinessException(MOVEMENT_WITH_AMOUNT_NULL);
        }
    }

    private void validateAmountMinValue(final BigDecimal amount) {
        if (amount.doubleValue() <= AMOUNT_MIN_VALUE) {
            throw new BusinessException(MOVEMENT_WITH_AMOUNT_BELOW_MIN_VALUE);
        }
    }

    private void validateAccountId(final UUID accountId) {
        if (Objects.isNull(accountId)) {
            throw new BusinessException(MOVEMENT_WITH_ACCOUNT_ID_NULL);
        }
    }

    private void validateMovementType(final MovementType movementType) {
        if (Objects.isNull(movementType)) {
            throw new BusinessException(MOVEMENT_WITH_MOVEMENT_TYPE_NULL);
        }
    }

    private void validateStatus(final MovementStatus status) {
        if (Objects.isNull(status)) {
            throw new BusinessException(MOVEMENT_WITH_STATUS_NULL);
        }
    }
}
