package com.connected.accountservice.application.service.account;

import com.connected.accountservice.application.converter.AccountInsertConverter;
import com.connected.accountservice.application.inputmodel.AccountInsertInputModel;
import com.connected.accountservice.application.inputmodel.AccountMoneyTransferInputModel;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModel;
import com.connected.accountservice.domain.validator.account.AccountInsertValidator;
import com.connected.accountservice.infrastructure.repository.account.AccountRepository;
import com.google.common.eventbus.EventBus;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AccountService {

    private static final String ACCOUNT_INSERT_INPUT_MODEL_NULL =
            "accountInsertInputModel cannot be null";

    private final AccountRepository accountRepository;

    private final MovementService movementService;

    private final EventBus eventBus;

    AccountService(final AccountRepository accountRepository,
                   final MovementService movementService,
                   final EventBus eventBus) {
        this.accountRepository = accountRepository;
        this.movementService = movementService;
        this.eventBus = eventBus;
    }

    public UUID insert(final AccountInsertInputModel accountInsertInputModel) {
        Objects.requireNonNull(accountInsertInputModel, ACCOUNT_INSERT_INPUT_MODEL_NULL);

        final var validator = new AccountInsertValidator();
        validator.validate(accountInsertInputModel);

        return insertByInputModel(accountInsertInputModel);
    }

    private UUID insertByInputModel(final AccountInsertInputModel accountInsertInputModel) {
        final var accountToInsert =
                AccountInsertConverter.convertToDomainObject(accountInsertInputModel);

        accountRepository.insert(accountToInsert);

        return accountToInsert.getId();
    }

    public void delete(final UUID accountIdToDelete) {
        accountRepository.delete(accountIdToDelete);
    }

    public List<AccountQueryModel> findAll() {
        return accountRepository.findAll();
    }

    public void transferMoney(final AccountMoneyTransferInputModel moneyTransferInputModel) {

    }
}
