package com.connected.accountservice;

class ApplicationTest {

    private ApplicationTest() {

    }

    static void start() {
        final var application = new Application();

        setupToTests(application);
    }

    private static void setupToTests(final Application application) {
        application.registerCustomConverters();
        application.registerEventBusListeners();
    }

}