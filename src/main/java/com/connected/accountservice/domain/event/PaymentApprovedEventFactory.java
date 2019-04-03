package com.connected.accountservice.domain.event;

import java.util.UUID;

public class PaymentApprovedEventFactory {

    private PaymentApprovedEventFactory() {

    }

    public static PaymentApprovedEvent createNew(final UUID accountIdFrom,
                                                 final UUID accountIdTo,
                                                 final UUID paymentMovement) {
        return new PaymentApprovedEvent(accountIdFrom, accountIdTo, paymentMovement);
    }
}
