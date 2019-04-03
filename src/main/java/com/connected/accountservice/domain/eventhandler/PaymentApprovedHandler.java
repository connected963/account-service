package com.connected.accountservice.domain.eventhandler;

import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.domain.event.CreditAmountToReceiverFailedEventFactory;
import com.connected.accountservice.domain.event.PaymentApprovedEvent;
import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.movement.MovementFactory;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.Objects;

public class PaymentApprovedHandler {

    private final AccountService accountService;

    private final MovementService movementService;

    private final EventBus eventBus;

    PaymentApprovedHandler(final AccountService accountService,
                           final MovementService movementService,
                           final EventBus eventBus) {
        this.accountService = accountService;
        this.movementService = movementService;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void paymentApprovedHandler(final PaymentApprovedEvent paymentApprovedEvent) {
        try {
            creditAmountToReceiverAccount(paymentApprovedEvent);
        } catch (Exception e) {
            postCreditAmountToReceiverFailedEvent(paymentApprovedEvent);
        }
    }

    private void creditAmountToReceiverAccount(
            final PaymentApprovedEvent paymentApprovedEvent) {
        final var receiverAccount = accountService.findAccountById(
                paymentApprovedEvent.getAccountIdTo());
        final var paymentMovement = movementService.findMovementById(
                paymentApprovedEvent.getPaymentMovementId());
        final var receiptMovement = createReceiptMovement(receiverAccount, paymentMovement);

        creditReceiptAccount(receiverAccount, receiptMovement);
        completePaymentMovement(paymentMovement);
    }

    private Movement createReceiptMovement(final Account receiverAccount,
                                           final Movement paymentMovement) {
        return MovementFactory.createNewInputMovement(paymentMovement.getAmount(),
                receiverAccount.getId());
    }

    private void creditReceiptAccount(final Account receiverAccount,
                                      final Movement receiptMovement) {
        final var receiverAccountRecalculated =
                receiverAccount.recalculateBalanceWithMovement(receiptMovement);
        final var receiptMovementCompleted = receiptMovement.complete();

        accountService.update(receiverAccountRecalculated);
        movementService.save(receiptMovementCompleted);
    }

    private void completePaymentMovement(final Movement paymentMovement) {
        final var paymentMovementCompleted = paymentMovement.complete();

        movementService.save(paymentMovementCompleted);
    }

    private void postCreditAmountToReceiverFailedEvent(
            final PaymentApprovedEvent paymentApprovedEvent) {
        final var creditAmountToReceiverFailedEvent =
                CreditAmountToReceiverFailedEventFactory.createNew(
                        paymentApprovedEvent.getAccountIdFrom(),
                        paymentApprovedEvent.getPaymentMovementId());

        eventBus.post(creditAmountToReceiverFailedEvent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentApprovedHandler that = (PaymentApprovedHandler) o;
        return Objects.equals(accountService, that.accountService) &&
                Objects.equals(movementService, that.movementService) &&
                Objects.equals(eventBus, that.eventBus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountService, movementService, eventBus);
    }
}
