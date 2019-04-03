package com.connected.accountservice.domain.eventhandler;

import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.movement.MovementService;
import com.google.common.eventbus.EventBus;

public class PaymentApprovedHandlerFactory {

    private PaymentApprovedHandlerFactory() {

    }

    public static PaymentApprovedHandler createNew(final AccountService accountService,
                                                   final MovementService movementService,
                                                   final EventBus eventBus) {
        return new PaymentApprovedHandler(accountService, movementService, eventBus);
    }
}
