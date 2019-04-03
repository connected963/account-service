package com.connected.accountservice.domain.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CreditAmountToReceiverFailedEventFactoryTest {

    @Test
    void givenEventData_mustCreateRespectiveEvent() {
        final var accountIdFrom = UUID.randomUUID();
        final var movementId = UUID.randomUUID();

        final var eventCreated = CreditAmountToReceiverFailedEventFactory.createNew(
                accountIdFrom, movementId);

        final var eventExpected = new CreditAmountToReceiverFailedEvent(
                accountIdFrom, movementId);

        Assertions.assertThat(eventCreated).isEqualTo(eventExpected);
    }

}