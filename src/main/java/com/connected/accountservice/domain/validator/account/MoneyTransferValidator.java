package com.connected.accountservice.domain.validator.account;

import com.connected.accountservice.application.inputmodel.MoneyTransferInputModel;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.validator.common.Validator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class MoneyTransferValidator implements Validator<MoneyTransferInputModel> {

    private static final Double AMOUNT_MIN_VALUE = 0.0;

    private static final String MONEY_TRANSFER_WITH_NULL_ACCOUNT = "Account cannot be null";
    private static final String MONEY_TRANSFER_WITH_NULL_AMOUNT = "Amount cannot be null";
    private static final String AMOUNT_BELOW_MIN_VALUE =
            String.format("Amount cannot be less than or equals to %f", AMOUNT_MIN_VALUE);

    @Override
    public void validate(final MoneyTransferInputModel moneyTransferInputModel) {
        validateAccountId(moneyTransferInputModel.getAccountIdFrom());
        validateAccountId(moneyTransferInputModel.getAccountIdTo());
        validateAmount(moneyTransferInputModel.getAmount());
    }

    private void validateAccountId(final UUID accountId) {
        if (Objects.isNull(accountId)) {
            throw new BusinessException(MONEY_TRANSFER_WITH_NULL_ACCOUNT);
        }
    }

    private void validateAmount(final BigDecimal amount) {
        validateAmountNonNull(amount);
        validateAmountMinValue(amount);
    }

    private void validateAmountNonNull(final BigDecimal amount) {
        if (Objects.isNull(amount)) {
            throw new BusinessException(MONEY_TRANSFER_WITH_NULL_AMOUNT);
        }
    }

    private void validateAmountMinValue(final BigDecimal amount) {
        if (amount.doubleValue() < AMOUNT_MIN_VALUE) {
            throw new BusinessException(AMOUNT_BELOW_MIN_VALUE);
        }
    }
}
