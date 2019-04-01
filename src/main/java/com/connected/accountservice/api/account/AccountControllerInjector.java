package com.connected.accountservice.api.account;

import com.connected.accountservice.application.service.AccountServiceInjector;

class AccountControllerInjector {

    private AccountControllerInjector() {

    }

    static AccountController inject() {
        return AccountControllerInstance.ACCOUNT_CONTROLLER_INSTANCE;
    }

    private static class AccountControllerInstance {
        private static final AccountController ACCOUNT_CONTROLLER_INSTANCE =
                createInstance();

        private static AccountController createInstance() {
            final var accountService = AccountServiceInjector.inject();
            return new AccountController(accountService);
        }
    }
}
