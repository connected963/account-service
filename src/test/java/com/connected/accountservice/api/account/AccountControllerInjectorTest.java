package com.connected.accountservice.api.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountControllerInjectorTest {

    @Test
    void mustGetAccountControllerInstance() {
        final var accountController = AccountControllerInjector.inject();

        Assertions.assertThat(accountController).isNotNull();
    }

}