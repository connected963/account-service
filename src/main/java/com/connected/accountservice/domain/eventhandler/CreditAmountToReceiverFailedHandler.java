package com.connected.accountservice.domain.eventhandler;

import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.domain.event.CreditAmountToReceiverFailedEvent;
import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.model.movement.Movement;
import com.google.common.eventbus.Subscribe;

import java.util.Objects;

public class CreditAmountToReceiverFailedHandler {

    private final AccountService accountService;

    private final MovementService movementService;

    CreditAmountToReceiverFailedHandler(final AccountService accountService,
                                        final MovementService movementService) {
        this.accountService = accountService;
        this.movementService = movementService;
    }

    @Subscribe
    void abortTransferMoney(
            final CreditAmountToReceiverFailedEvent creditAmountToReceiverFailedEvent) {
        final var payingAccount = accountService.findAccountById(
                creditAmountToReceiverFailedEvent.getAccountIdFrom());
        final var payingMovement = movementService.findMovementById(
                creditAmountToReceiverFailedEvent.getPaymentMovementId());

        revertAccountMovement(payingAccount, payingMovement);
        abortMovement(payingMovement);
    }

    private void revertAccountMovement(final Account payingAccount,
                                       final Movement payingMovement) {
        final var accountWithRevertedMovement = payingAccount.revertMovement(payingMovement);

        accountService.update(accountWithRevertedMovement);
    }

    private void abortMovement(final Movement payingMovement) {
        final var abortedMovement = payingMovement.abort();

        movementService.update(abortedMovement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditAmountToReceiverFailedHandler that = (CreditAmountToReceiverFailedHandler) o;
        return Objects.equals(accountService, that.accountService) &&
                Objects.equals(movementService, that.movementService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountService, movementService);
    }
}
