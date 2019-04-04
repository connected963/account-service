package com.connected.accountservice.api.account;

import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;

public class AccountRouter {

    static final String ACCOUNT_DELETE_PARAMETER = "id";

    private static final String ACCOUNT_PATH = "accounts";
    private static final String ACCOUNT_PATH_DELETE_PARAMETER =
            String.format(":%s", ACCOUNT_DELETE_PARAMETER);
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
            ApiBuilder.delete(ACCOUNT_PATH_DELETE_PARAMETER, accountController::delete);
            ApiBuilder.post(ACCOUNT_TRANSFER_PATH, accountController::transferMoney);
        });
    }
}
