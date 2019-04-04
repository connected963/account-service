package com.connected.accountservice.infrastructure.repository.account;

import com.connected.accountservice.infrastructure.database.jdbi.JdbiInjector;

public class AccountRepositoryInjector {

    private AccountRepositoryInjector() {

    }

    public static AccountRepository inject() {
        return AccountRepositoryInstance.ACCOUNT_REPOSITORY_INSTANCE;
    }

    private static class AccountRepositoryInstance {
        private static final AccountRepository ACCOUNT_REPOSITORY_INSTANCE =
                createInstance();

        private static AccountRepository createInstance() {
            final var jdbi = JdbiInjector.inject();
            return jdbi.onDemand(AccountRepository.class);
        }
    }
}
