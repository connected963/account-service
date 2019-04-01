package com.connected.accountservice;

import com.connected.accountservice.api.account.AccountRouter;
import io.javalin.Javalin;

public class Application {

    private final Javalin app;

    private Application() {
        this.app = Javalin.create()
                .start(8080);
    }

    public static void main(String[] args) {
        new Application().createRoutes();
    }

    private void createRoutes() {
        AccountRouter.createRoutes(app);
    }

}
