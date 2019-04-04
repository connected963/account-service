package com.connected.accountservice.application.inputmodel;

import com.connected.accountservice.common.BigDecimalScale;
import com.connected.accountservice.common.defaultdata.AccountDefaultData;

import java.math.BigDecimal;
import java.util.UUID;

public class MoneyTransferInputModelTestFactory {

    private MoneyTransferInputModelTestFactory() {

    }

    public static MoneyTransferInputModel createAnDefault() {
        return new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(AccountDefaultData.id)
                .withAccountIdTo(UUID.randomUUID())
                .withAmount(BigDecimalScale.ONE)
                .build();
    }

    public static MoneyTransferInputModel createAnDefaultWithoutAccountFrom() {
        return new MoneyTransferInputModelTestBuilder()
                .withAccountIdTo(UUID.randomUUID())
                .withAmount(BigDecimalScale.ONE)
                .build();
    }

    public static MoneyTransferInputModel createAnDefaultWithoutAccountTo() {
        return new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(AccountDefaultData.id)
                .withAmount(BigDecimalScale.ONE)
                .build();
    }

    public static MoneyTransferInputModel createAnDefaultWithoutAmount() {
        return new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(AccountDefaultData.id)
                .withAccountIdTo(UUID.randomUUID())
                .build();
    }

    public static MoneyTransferInputModel createAnDefaultWithAmount(final BigDecimal amount) {
        return new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(AccountDefaultData.id)
                .withAccountIdTo(UUID.randomUUID())
                .withAmount(amount)
                .build();
    }

}
