package com.connected.accountservice.domain.model.account;

import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountFactoryTest {

    @Test
    void givenAccountData_mustCreateNewAccountEquivalent() {
        final var accountCreated = AccountFactory.createNewAccount(
                AccountDefaultData.balance, AccountDefaultData.overdraft);
        final var accountId = accountCreated.getId();


        final var accountExpected = new Account(accountId,
                AccountDefaultData.balance, AccountDefaultData.overdraft);

        Assertions.assertThat(accountCreated).isEqualTo(accountCreated);
        Assertions.assertThat(accountId).isNotNull();
    }
}