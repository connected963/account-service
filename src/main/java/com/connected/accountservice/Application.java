package com.connected.accountservice;

import com.connected.accountservice.api.account.AccountRouter;
import com.connected.accountservice.domain.eventbus.EventBusConfiguration;
import com.connected.accountservice.infrastructure.database.flyway.FlywayConfig;
import io.javalin.Javalin;
import io.javalin.validation.JavalinValidation;

import java.util.UUID;

public class Application {

    private static final Integer APPLICATION_PORT = 8080;

    private Javalin app;

    Application() {

    }

    public static void main(String[] args) {
        final var application = new Application();
        application.start();
    }

    private void start() {
        createServer();
        runMigrations();
        createRoutes();
        registerCustomConverters();
        registerEventBusListeners();
    }

    private void createServer() {
        this.app = Javalin.create()
                .start(APPLICATION_PORT);
    }

    private void createRoutes() {
        AccountRouter.createRoutes(app);
    }

    private void runMigrations() {
        FlywayConfig.runMigrations();
    }

    void registerCustomConverters() {
        JavalinValidation.register(UUID.class, UUID::fromString);
    }

    void registerEventBusListeners() {
        EventBusConfiguration.registerListeners();
    }

}
