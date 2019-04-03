package com.connected.accountservice.domain.eventhandler;

import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.movement.MovementService;

public class CreditAmountToReceiverFailedHandlerFactory {

    private CreditAmountToReceiverFailedHandlerFactory() {

    }

    public static CreditAmountToReceiverFailedHandler createNew(
            final AccountService accountService,
            final MovementService movementService) {
        return new CreditAmountToReceiverFailedHandler(accountService, movementService);
    }
}
