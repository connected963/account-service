package com.connected.accountservice.application.converter;

import com.connected.accountservice.application.inputmodel.AccountInsertInputModel;
import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.model.account.AccountFactory;

public class AccountInsertConverter {

    private AccountInsertConverter() {
    }

    public static Account convertToDomainObject(
            final AccountInsertInputModel accountInsertInputModel) {
        return AccountFactory.createNewAccount(accountInsertInputModel.getOverdraft());
    }
}
