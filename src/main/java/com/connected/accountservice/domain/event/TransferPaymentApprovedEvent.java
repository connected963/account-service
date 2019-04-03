package com.connected.accountservice.domain.event;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class TransferPaymentApprovedEvent {

    private final UUID accountIdFrom;

    private final UUID accountIdTo;

    private final UUID paymentMovementId;

    TransferPaymentApprovedEvent(final UUID accountIdFrom,
                                 final UUID accountIdTo,
                                 final UUID paymentMovementId) {
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
        this.paymentMovementId = paymentMovementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferPaymentApprovedEvent that = (TransferPaymentApprovedEvent) o;
        return Objects.equals(accountIdFrom, that.accountIdFrom) &&
                Objects.equals(accountIdTo, that.accountIdTo) &&
                Objects.equals(paymentMovementId, that.paymentMovementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIdFrom, accountIdTo, paymentMovementId);
    }

    public UUID getAccountIdFrom() {
        return accountIdFrom;
    }

    public UUID getAccountIdTo() {
        return accountIdTo;
    }

    public UUID getPaymentMovementId() {
        return paymentMovementId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransferPaymentApprovedEvent.class.getSimpleName() + "[", "]")
                .add("accountIdFrom=" + accountIdFrom)
                .add("accountIdTo=" + accountIdTo)
                .add("paymentMovementId=" + paymentMovementId)
                .toString();
    }
}
