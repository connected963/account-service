package com.connected.accountservice.application.inputmodel;

import com.connected.accountservice.common.defaultdata.AccountDefaultData;

import java.math.BigDecimal;

public class AccountInsertInputModelFactory {

    private AccountInsertInputModelFactory() {
    }

    public static AccountInsertInputModel createAnDefault() {
        return new AccountInsertInputModel(AccountDefaultData.overdraft);
    }

    public static AccountInsertInputModel createAnDefaultWithoutOverdraft() {
        return new AccountInsertInputModel(null);
    }

    public static AccountInsertInputModel createAnDefaultWithOverdraft(final BigDecimal overdraft) {
        return new AccountInsertInputModel(overdraft);
    }

}
