package com.connected.accountservice.domain.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class PaymentApprovedEventFactoryTest {

    @Test
    void givenEventData_mustCreateRespectiveEvent() {
        final var accountIdFrom = UUID.randomUUID();
        final var accountIdTo = UUID.randomUUID();
        final var movementId = UUID.randomUUID();

        final var eventCreated = PaymentApprovedEventFactory.createNew(
                accountIdFrom, accountIdTo, movementId);

        final var eventExpected = new PaymentApprovedEvent(
                accountIdFrom, accountIdTo, movementId);

        Assertions.assertThat(eventCreated).isEqualTo(eventExpected);
    }

}