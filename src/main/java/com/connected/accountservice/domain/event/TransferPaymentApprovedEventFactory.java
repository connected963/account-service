package com.connected.accountservice.domain.event;

import java.util.UUID;

public class TransferPaymentApprovedEventFactory {

    private TransferPaymentApprovedEventFactory() {

    }

    public static TransferPaymentApprovedEvent createNew(final UUID accountIdFrom,
                                                         final UUID accountIdTo,
                                                         final UUID paymentMovement) {
        return new TransferPaymentApprovedEvent(accountIdFrom, accountIdTo, paymentMovement);
    }
}
