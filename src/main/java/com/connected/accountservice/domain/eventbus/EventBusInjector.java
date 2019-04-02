package com.connected.accountservice.domain.eventbus;

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
                new AsyncEventBus(Executors.newCachedThreadPool());
    }

}
