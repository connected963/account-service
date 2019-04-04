package com.connected.accountservice;

import com.connected.accountservice.api.account.AccountRouter;
import com.connected.accountservice.domain.eventbus.EventBusConfiguration;
import com.connected.accountservice.infrastructure.database.flyway.FlywayConfig;
import io.javalin.Javalin;
import io.javalin.validation.JavalinValidation;

import java.util.UUID;

public class Application {

    private final Javalin app;

    private Application() {
        this.app = Javalin.create()
                .start(8080);
    }

    public static void main(String[] args) {
        final var application = new Application();
        application.start();
    }

    private void start() {
        runMigrations();
        createRoutes();
        registerCustomConverters();
        registerEventBusListeners();
    }

    private void createRoutes() {
        AccountRouter.createRoutes(app);
    }

    private void runMigrations() {
        FlywayConfig.runMigrations();
    }

    private void registerCustomConverters() {
        JavalinValidation.register(UUID.class, UUID::fromString);
    }

    private void registerEventBusListeners() {
        EventBusConfiguration.registerListeners();
    }

}
