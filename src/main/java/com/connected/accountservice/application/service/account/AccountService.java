package com.connected.accountservice.application.service.account;

import com.connected.accountservice.application.converter.AccountInsertConverter;
import com.connected.accountservice.application.converter.MoneyTransferConverter;
import com.connected.accountservice.application.inputmodel.AccountInsertInputModel;
import com.connected.accountservice.application.inputmodel.MoneyTransferInputModel;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.domain.event.PaymentApprovedEventFactory;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModel;
import com.connected.accountservice.domain.validator.account.AccountInsertValidator;
import com.connected.accountservice.domain.validator.account.MoneyTransferValidator;
import com.connected.accountservice.infrastructure.repository.account.AccountRepository;
import com.google.common.eventbus.EventBus;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AccountService {

    private static final String ACCOUNT_INSERT_INPUT_MODEL_NULL =
            "accountInsertInputModel cannot be null";
    private static final String ACCOUNT_DELETE_ID_NULL =
            "accountIdToDelete cannot be null";
    private static final String MONEY_TRANSFER_INPUT_MODEL_NULL =
            "moneyTransferInputModel cannot be null";
    private static final String ACCOUNT_NOT_FOUND = "Account not found";
    private static final String ACCOUNT_TO_UPDATE_NULL = "Account cannot be null";


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

    public void update(final Account accountToUpdate) {
        Objects.requireNonNull(accountToUpdate, ACCOUNT_TO_UPDATE_NULL);

        accountRepository.update(accountToUpdate);
    }

    public void delete(final UUID accountIdToDelete) {
        Objects.requireNonNull(accountIdToDelete, ACCOUNT_DELETE_ID_NULL);
        accountRepository.delete(accountIdToDelete);
    }

    public List<AccountQueryModel> findAll() {
        return accountRepository.findAll();
    }

    public void transferMoney(final MoneyTransferInputModel moneyTransferInputModel) {
        Objects.requireNonNull(moneyTransferInputModel, MONEY_TRANSFER_INPUT_MODEL_NULL);

        final var validator = new MoneyTransferValidator();
        validator.validate(moneyTransferInputModel);

        payTransfer(moneyTransferInputModel);
    }

    private void payTransfer(final MoneyTransferInputModel moneyTransferInputModel) {
        final var paymentMovement = generatePaymentMovement(moneyTransferInputModel);
        final var accountPayerId = moneyTransferInputModel.getAccountIdFrom();
        final var accountPayer = findAccountById(accountPayerId);

        //TODO this must be executed in a transaction
        updateAccountBalanceWithPayment(paymentMovement, accountPayer);
        postPaymentApprovedEvent(moneyTransferInputModel, paymentMovement);
    }

    private Movement generatePaymentMovement(final MoneyTransferInputModel moneyTransferInputModel) {
        return MoneyTransferConverter.convertToAccountFromOutputMovement(
                moneyTransferInputModel);
    }

    public Account findAccountById(final UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException(ACCOUNT_NOT_FOUND));
    }

    private void updateAccountBalanceWithPayment(final Movement paymentMovement,
                                                 final Account accountPayer) {
        final var accountWithUpdatedBalance =
                accountPayer.recalculateBalanceWithMovement(paymentMovement);

        accountRepository.update(accountWithUpdatedBalance);
        movementService.insert(paymentMovement);
    }

    private void postPaymentApprovedEvent(final MoneyTransferInputModel moneyTransferInputModel,
                                          final Movement paymentMovement) {
        final var paymentApprovedEvent = PaymentApprovedEventFactory.createNew(
                moneyTransferInputModel.getAccountIdFrom(),
                moneyTransferInputModel.getAccountIdTo(),
                paymentMovement.getId());

        eventBus.post(paymentApprovedEvent);
    }
}
