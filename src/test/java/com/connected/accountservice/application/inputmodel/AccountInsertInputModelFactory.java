package com.connected.accountservice.application.inputmodel;

import com.connected.accountservice.common.defaultdata.AccountDefaultData;

import java.math.BigDecimal;

public class AccountInsertInputModelFactory {

    private AccountInsertInputModelFactory() {
    }

    public static AccountInsertInputModel createAnDefault() {
        return new AccountInsertInputModel(AccountDefaultData.balance,
                AccountDefaultData.overdraft);
    }

    public static AccountInsertInputModel createAnDefaultWithoutBalance() {
        return new AccountInsertInputModel(null, AccountDefaultData.overdraft);
    }

    public static AccountInsertInputModel createAnDefaultWithoutOverdraft() {
        return new AccountInsertInputModel(AccountDefaultData.balance, null);
    }

    public static AccountInsertInputModel createAnDefaultWithBalance(final BigDecimal balance) {
        return new AccountInsertInputModel(balance, AccountDefaultData.overdraft);
    }

    public static AccountInsertInputModel createAnDefaultWithOverdraft(final BigDecimal overdraft) {
        return new AccountInsertInputModel(AccountDefaultData.balance, overdraft);
    }

}
