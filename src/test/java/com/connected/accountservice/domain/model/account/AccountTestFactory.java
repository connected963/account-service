package com.connected.accountservice.domain.model.account;

import com.connected.accountservice.common.defaultdata.AccountDefaultData;

import java.math.BigDecimal;

public class AccountTestFactory {

    private AccountTestFactory() {

    }

    public static Account createAnDefault() {
        return new AccountBuilder()
                .withId(AccountDefaultData.id)
                .withBalance(AccountDefaultData.balance)
                .withOverdraft(AccountDefaultData.overdraft)
                .build();
    }

    public static Account createAnDefaultWithBalance(final BigDecimal balance) {
        return new AccountBuilder()
                .withId(AccountDefaultData.id)
                .withBalance(balance)
                .withOverdraft(AccountDefaultData.overdraft)
                .build();
    }

    public static Account createAnDefaultWithOverdraft(final BigDecimal overdraft) {
        return new AccountBuilder()
                .withId(AccountDefaultData.id)
                .withBalance(AccountDefaultData.balance)
                .withOverdraft(overdraft)
                .build();
    }

    public static Account createAnDefaultWithBalanceAndOverdraft(final BigDecimal balance,
                                                                 final BigDecimal overdraft) {
        return new AccountBuilder()
                .withId(AccountDefaultData.id)
                .withBalance(balance)
                .withOverdraft(overdraft)
                .build();
    }

}
