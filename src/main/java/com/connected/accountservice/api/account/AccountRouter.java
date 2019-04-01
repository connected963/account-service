package com.connected.accountservice.api.account;

import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;

public class AccountRouter {

    private static final String ACCOUNT_PATH = "accounts";
    private static final String ACCOUNT_TRANSFER_PATH = "transfer";

    private final Javalin app;

    private final AccountController accountController;

    private AccountRouter(final Javalin app,
                          final AccountController accountController) {
        this.app = app;
        this.accountController = accountController;
    }

    public static void createRoutes(final Javalin app) {
        final var accountController = AccountControllerInjector.inject();
        final var router = new AccountRouter(app, accountController);

        router.addRoutes();
    }

    private void addRoutes() {
        app.routes(this::createAccountRoutes);
    }

    private void createAccountRoutes() {
        ApiBuilder.path(ACCOUNT_PATH, () -> {
            ApiBuilder.get(accountController::findAll);
            ApiBuilder.post(accountController::insert);
            ApiBuilder.put(accountController::update);
            ApiBuilder.delete(accountController::delete);
            ApiBuilder.post(ACCOUNT_TRANSFER_PATH, accountController::transferMoney);
        });
    }
}
