package com.connected.accountservice.application.service.account;

import com.connected.accountservice.application.service.movement.MovementServiceInjector;
import com.connected.accountservice.domain.eventbus.EventBusInjector;
import com.connected.accountservice.infrastructure.repository.account.AccountRepositoryInjector;

public class AccountServiceInjector {

    private AccountServiceInjector() {

    }

    public static AccountService inject() {
        return AccountServiceInstance.ACCOUNT_SERVICE_INSTANCE;
    }

    private static class AccountServiceInstance {
        private static final AccountService ACCOUNT_SERVICE_INSTANCE =
                createInstance();

        private static AccountService createInstance() {
            final var accountRepository = AccountRepositoryInjector.inject();
            final var movementService = MovementServiceInjector.inject();
            final var eventBus = EventBusInjector.inject();

            return new AccountService(accountRepository, movementService, eventBus);
        }
    }
}
