package com.connected.accountservice.domain.eventbus;

import com.connected.accountservice.application.service.account.AccountServiceInjector;
import com.connected.accountservice.application.service.movement.MovementServiceInjector;
import com.connected.accountservice.domain.eventhandler.CreditAmountToReceiverFailedHandler;
import com.connected.accountservice.domain.eventhandler.CreditAmountToReceiverFailedHandlerFactory;
import com.connected.accountservice.domain.eventhandler.PaymentApprovedHandler;
import com.connected.accountservice.domain.eventhandler.PaymentApprovedHandlerFactory;
import com.google.common.eventbus.EventBus;

public class EventBusConfiguration {

    private EventBusConfiguration() {

    }

    public static void registerListeners() {
        final var eventBus = EventBusInjector.inject();
        addListeners(eventBus);
    }

    private static void addListeners(final EventBus eventBusInstance) {
        eventBusInstance.register(createPaymentApprovedHandler(eventBusInstance));
        eventBusInstance.register(createCreditAmountToReceiverHandler());
    }

    private static PaymentApprovedHandler createPaymentApprovedHandler(
            final EventBus eventBusInstance) {
        final var accountService = AccountServiceInjector.inject();
        final var movementService = MovementServiceInjector.inject();

        return PaymentApprovedHandlerFactory.createNew(accountService,
                movementService, eventBusInstance);
    }

    private static CreditAmountToReceiverFailedHandler createCreditAmountToReceiverHandler() {
        final var accountService = AccountServiceInjector.inject();
        final var movementService = MovementServiceInjector.inject();

        return CreditAmountToReceiverFailedHandlerFactory.createNew(accountService,
                movementService);
    }

}
