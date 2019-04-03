package com.connected.accountservice.domain.event;

import java.util.UUID;

public class CreditAmountToReceiverFailedEventFactory {

    private CreditAmountToReceiverFailedEventFactory() {

    }

    public static CreditAmountToReceiverFailedEvent createNew(final UUID accountIdFrom,
                                                              final UUID paymentMovement) {
        return new CreditAmountToReceiverFailedEvent(accountIdFrom, paymentMovement);
    }
}
