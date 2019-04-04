package com.connected.accountservice.infrastructure.repository.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountRepositoryInjectorTest {

    @Test
    void mustGetAccountRepositoryInstance() {
        final var accountRepository = AccountRepositoryInjector.inject();

        Assertions.assertThat(accountRepository).isNotNull();
    }

}