package com.connected.accountservice.application.service;

public class AccountServiceInjector {

    private AccountServiceInjector() {

    }

    public static AccountService inject() {
        return AccountServiceInstance.ACCOUNT_CONTROLLER_INSTANCE;
    }

    private static class AccountServiceInstance {
        private static final AccountService ACCOUNT_CONTROLLER_INSTANCE =
                new AccountService(mock -> {
                });
    }

}
