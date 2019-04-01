package com.connected.accountservice.application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountServiceInjectorTest {

    @Test
    void mustGetAccountServiceInstance() {
        final var accountService = AccountServiceInjector.inject();

        Assertions.assertThat(accountService).isNotNull();
    }

}