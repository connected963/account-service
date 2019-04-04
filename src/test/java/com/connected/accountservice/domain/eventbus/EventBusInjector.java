package com.connected.accountservice.domain.eventbus;

import com.google.common.eventbus.EventBus;

public class EventBusInjector {

    private EventBusInjector() {

    }

    public static EventBus inject() {
        return EventBusInstance.EVENT_BUS_INSTANCE;
    }

    private static class EventBusInstance {
        private static final EventBus EVENT_BUS_INSTANCE =
                createInstance();

        private static EventBus createInstance() {
            return new EventBus();
        }
    }

}
