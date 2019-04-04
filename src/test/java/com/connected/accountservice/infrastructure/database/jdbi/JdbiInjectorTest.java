package com.connected.accountservice.infrastructure.database.jdbi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JdbiInjectorTest {

    @Test
    void mustGetAccountJdbiInstance() {
        final var jdbi = JdbiInjector.inject();

        Assertions.assertThat(jdbi).isNotNull();
    }

}