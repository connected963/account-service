package com.connected.accountservice;

import com.connected.accountservice.api.account.AccountRouter;
import com.connected.accountservice.infrastructure.database.flyway.FlywayConfig;
import io.javalin.Javalin;

public class Application {

    private final Javalin app;

    private Application() {
        this.app = Javalin.create()
                .start(8080);
    }

    public static void main(String[] args) {
        final var application = new Application();

        application.runMigrations();
        application.createRoutes();
    }

    private void createRoutes() {
        AccountRouter.createRoutes(app);
    }

    private void runMigrations() {
        FlywayConfig.runMigrations();
    }

}
