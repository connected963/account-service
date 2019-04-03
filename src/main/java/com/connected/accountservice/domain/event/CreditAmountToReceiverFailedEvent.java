package com.connected.accountservice.domain.event;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class CreditAmountToReceiverFailedEvent {

    private final UUID accountIdFrom;

    private final UUID paymentMovementId;

    CreditAmountToReceiverFailedEvent(final UUID accountIdFrom,
                                      final UUID paymentMovementId) {
        this.accountIdFrom = accountIdFrom;
        this.paymentMovementId = paymentMovementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditAmountToReceiverFailedEvent that = (CreditAmountToReceiverFailedEvent) o;
        return Objects.equals(accountIdFrom, that.accountIdFrom) &&
                Objects.equals(paymentMovementId, that.paymentMovementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIdFrom, paymentMovementId);
    }

    public UUID getAccountIdFrom() {
        return accountIdFrom;
    }

    public UUID getPaymentMovementId() {
        return paymentMovementId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreditAmountToReceiverFailedEvent.class.getSimpleName() + "[", "]")
                .add("accountIdFrom=" + accountIdFrom)
                .add("paymentMovementId=" + paymentMovementId)
                .toString();
    }
}
