package com.connected.accountservice.application.service;

import com.connected.accountservice.application.converter.AccountInsertConverter;
import com.connected.accountservice.application.inputmodel.AccountInsertInputModel;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModel;
import com.connected.accountservice.domain.validator.account.AccountInsertValidator;
import com.connected.accountservice.infrastructure.repository.AccountRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AccountService {

    private static final String ACCOUNT_INSERT_INPUT_MODEL_NULL =
            "accountInsertInputModel cannot be null";

    private final AccountRepository accountRepository;

    AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
}
