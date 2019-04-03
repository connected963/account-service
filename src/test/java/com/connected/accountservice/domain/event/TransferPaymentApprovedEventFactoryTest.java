package com.connected.accountservice.domain.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TransferPaymentApprovedEventFactoryTest {

    @Test
    void givenEventData_mustCreateRespectiveEvent() {
        final var accountIdFrom = UUID.randomUUID();
        final var accountIdTo = UUID.randomUUID();
        final var movementId = UUID.randomUUID();

        final var eventCreated = TransferPaymentApprovedEventFactory.createNew(
                accountIdFrom, accountIdTo, movementId);

        final var eventExpected = new TransferPaymentApprovedEvent(
                accountIdFrom, accountIdTo, movementId);

        Assertions.assertThat(eventCreated).isEqualTo(eventExpected);
    }

}