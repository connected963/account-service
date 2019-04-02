package com.connected.accountservice.domain.model.account;

import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountFactoryTest {

    @Test
    void givenAccountData_mustCreateNewAccountEquivalent() {
        final var accountCreated = AccountFactory.createNewAccount(AccountDefaultData.overdraft);
        final var accountId = accountCreated.getId();

        final var accountExpected = new Account(accountId,
                BigDecimal.ZERO, AccountDefaultData.overdraft);

        Assertions.assertThat(accountCreated).isEqualTo(accountExpected);
        Assertions.assertThat(accountId).isNotNull();
    }
}