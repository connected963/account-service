package com.connected.accountservice.domain.model.account;

import com.connected.accountservice.common.BigDecimalScale;
import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountFactoryTest {

    @Test
    void givenAccountData_mustCreateNewAccountEquivalent() {
        final var accountCreated = AccountFactory.createNewAccount(AccountDefaultData.overdraft);
        final var accountId = accountCreated.getId();

        final var accountExpected = new Account(accountId,
                BigDecimalScale.ZERO, AccountDefaultData.overdraft);

        Assertions.assertThat(accountCreated).isEqualTo(accountExpected);
        Assertions.assertThat(accountId).isNotNull();
    }

    @Test
    void givenAccountData_mustCreateExistentAccountEquivalent() {
        final var accountCreated = AccountFactory.createExistentAccount(AccountDefaultData.id,
                AccountDefaultData.balance, AccountDefaultData.overdraft);

        final var accountExpected = new Account(AccountDefaultData.id,
                AccountDefaultData.balance, AccountDefaultData.overdraft);

        Assertions.assertThat(accountCreated).isEqualTo(accountExpected);
    }
}