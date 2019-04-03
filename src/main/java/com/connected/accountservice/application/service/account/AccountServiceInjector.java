package com.connected.accountservice.application.service.account;

import com.connected.accountservice.application.service.movement.MovementServiceInjector;
import com.connected.accountservice.domain.eventbus.EventBusInjector;
import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModel;
import com.connected.accountservice.infrastructure.repository.account.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountServiceInjector {

    private AccountServiceInjector() {

    }

    public static AccountService inject() {
        return AccountServiceInstance.ACCOUNT_SERVICE_INSTANCE;
    }

    private static class AccountServiceInstance {
        //TODO replace with some implementation
        private static final AccountService ACCOUNT_SERVICE_INSTANCE =
                createInstance();

        private static AccountService createInstance() {
            final var accountRepository = getAccountRepository();
            final var movementService = MovementServiceInjector.inject();
            final var eventBus = EventBusInjector.inject();

            return new AccountService(accountRepository, movementService, eventBus);
        }

        private static AccountRepository getAccountRepository() {
            return new AccountRepository() {
                @Override
                public void insert(Account accountToInsert) {

                }

                @Override
                public void delete(UUID accountIdToDelete) {

                }

                @Override
                public List<AccountQueryModel> findAll() {
                    return null;
                }

                @Override
                public void update(Account accountToUpdate) {

                }

                @Override
                public Optional<Account> findById(UUID accountId) {
                    return Optional.empty();
                }
            };
        }
    }
}
