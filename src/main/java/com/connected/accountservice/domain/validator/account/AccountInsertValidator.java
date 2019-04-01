package com.connected.accountservice.domain.validator.account;

import com.connected.accountservice.application.inputmodel.AccountInsertInputModel;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.validator.common.Validator;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountInsertValidator implements Validator<AccountInsertInputModel> {

    private static final Double BALANCE_MIN_VALUE = 0.0;

    private static final String ACCOUNT_WITH_NULL_BALANCE = "Balance cannot be null";
    private static final String ACCOUNT_WITH_BALANCE_BELOW_MIN_VALUE =
            String.format("Balance cannot be below than %f", BALANCE_MIN_VALUE);

    private static final Double OVERDRAFT_MIN_VALUE = 0.0;

    private static final String ACCOUNT_WITH_NULL_OVERDRAFT = "Overdraft cannot be null";
    private static final String ACCOUNT_WITH_OVERDRAFT_BELOW_MIN_VALUE =
            String.format("Overdraft cannot be below than %f", BALANCE_MIN_VALUE);

    @Override
    public void validate(final AccountInsertInputModel account) {
        validateBalance(account.getBalance());
        validateOverdraft(account.getOverdraft());
    }

    private void validateBalance(final BigDecimal balance) {
        validateBalanceNonNull(balance);
        validateBalanceMinValue(balance);
    }

    private void validateBalanceNonNull(final BigDecimal balance) {
        if (Objects.isNull(balance)) {
            throw new BusinessException(ACCOUNT_WITH_NULL_BALANCE);
        }
    }

    private void validateBalanceMinValue(final BigDecimal balance) {
        if (balance.doubleValue() < BALANCE_MIN_VALUE) {
            throw new BusinessException(ACCOUNT_WITH_BALANCE_BELOW_MIN_VALUE);
        }
    }

    private void validateOverdraft(final BigDecimal overdraft) {
        validateOverdraftNonNull(overdraft);
        validateOverdraftMinValue(overdraft);
    }

    private void validateOverdraftNonNull(final BigDecimal overdraft) {
        if (Objects.isNull(overdraft)) {
            throw new BusinessException(ACCOUNT_WITH_NULL_OVERDRAFT);
        }
    }

    private void validateOverdraftMinValue(final BigDecimal overdraft) {
        if (overdraft.doubleValue() < OVERDRAFT_MIN_VALUE) {
            throw new BusinessException(ACCOUNT_WITH_OVERDRAFT_BELOW_MIN_VALUE);
        }
    }
}
