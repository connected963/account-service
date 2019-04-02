package com.connected.accountservice.domain.eventbus;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EventBusInjectorTest {

    @Test
    void mustGetEventBusInstance() {
        final var eventBus = EventBusInjector.inject();

        Assertions.assertThat(eventBus).isNotNull();
    }

}