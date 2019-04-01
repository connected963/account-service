package com.connected.accountservice.api.account;

class AccountControllerInjector {

    private AccountControllerInjector() {

    }

    static AccountController inject() {
        return AccountControllerInstance.ACCOUNT_CONTROLLER_INSTANCE;
    }

    private static class AccountControllerInstance {
        private static final AccountController ACCOUNT_CONTROLLER_INSTANCE =
                new AccountController();
    }

}
