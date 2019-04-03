package com.connected.accountservice.domain.eventbus;

import com.connected.accountservice.application.service.account.AccountServiceInjector;
import com.connected.accountservice.application.service.movement.MovementServiceInjector;
import com.connected.accountservice.domain.eventhandler.CreditAmountToReceiverFailedHandler;
import com.connected.accountservice.domain.eventhandler.CreditAmountToReceiverFailedHandlerFactory;
import com.connected.accountservice.domain.eventhandler.PaymentApprovedHandler;
import com.connected.accountservice.domain.eventhandler.PaymentApprovedHandlerFactory;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

public class EventBusInjector {

    private EventBusInjector() {

    }

    public static EventBus inject() {
        return EventBusInjector.EventBusInstance.EVENT_BUS_INSTANCE;
    }

    private static class EventBusInstance {
        private static final EventBus EVENT_BUS_INSTANCE =
                createInstance();

        private static EventBus createInstance() {
            final var eventBusInstance = new AsyncEventBus(Executors.newCachedThreadPool());

            addListeners(eventBusInstance);

            return eventBusInstance;
        }

        private static void addListeners(final AsyncEventBus eventBusInstance) {
            eventBusInstance.register(createPaymentApprovedHandler(eventBusInstance));
            eventBusInstance.register(createCreditAmountToReceiverHandler());
        }

        private static PaymentApprovedHandler createPaymentApprovedHandler(
                final AsyncEventBus eventBusInstance) {
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

}
