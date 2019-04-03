package com.connected.accountservice.domain.event;

import java.util.UUID;

public class PaymentApprovedEventTestBuilder {

    private UUID accountIdFrom;

    private UUID accountIdTo;

    private UUID paymentMovementId;

    public PaymentApprovedEventTestBuilder() {

    }

    public PaymentApprovedEventTestBuilder withAccountIdFrom(final UUID accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
        return this;
    }

    public PaymentApprovedEventTestBuilder withAccountIdTo(final UUID accountIdTo) {
        this.accountIdTo = accountIdTo;
        return this;
    }

    public PaymentApprovedEventTestBuilder withPaymentMovementId(final UUID paymentMovementId) {
        this.paymentMovementId = paymentMovementId;
        return this;
    }

    public PaymentApprovedEvent build() {
        return new PaymentApprovedEvent(accountIdFrom, accountIdTo, paymentMovementId);
    }

}
