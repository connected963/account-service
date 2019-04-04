package com.connected.accountservice.api.account;

import com.connected.accountservice.BaseIntegrationTest;
import com.connected.accountservice.application.inputmodel.MoneyTransferInputModelTestBuilder;
import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.account.AccountServiceInjector;
import com.connected.accountservice.common.BigDecimalScale;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.account.AccountBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountControllerTest extends BaseIntegrationTest {

    //These accounts are created within V2 migrations file
    private static final UUID ACCOUNT_ID_FROM = UUID.fromString("4b876f28-4851-43e2-9519-865b04998b5f");
    private static final UUID ACCOUNT_ID_TO = UUID.fromString("1d7fc333-c686-4435-85d4-822ddc4fc85e");
    private static final BigDecimal DEFAULT_OVERDRAFT = BigDecimalScale.valueOf(100);

    private final AccountService accountService;

    private final AccountController accountController;

    AccountControllerTest() {
        this.accountService = AccountServiceInjector.inject();
        this.accountController = new AccountController(accountService);
    }

    @Test
    void givenTransferenceWithAmountLessThanAccountFromBalance_mustTransfer() {
        final var transference = new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(ACCOUNT_ID_FROM)
                .withAccountIdTo(ACCOUNT_ID_TO)
                .withAmount(BigDecimalScale.valueOf(50))
                .build();

        accountController.transferMoney(transference);

        final var accountFromUpdated = accountService.findAccountById(ACCOUNT_ID_FROM);
        final var accountFromExpected = new AccountBuilder()
                .withId(ACCOUNT_ID_FROM)
                .withBalance(BigDecimalScale.valueOf(50))
                .withOverdraft(DEFAULT_OVERDRAFT)
                .build();
        Assertions.assertThat(accountFromUpdated).isEqualTo(accountFromExpected);

        final var accountToUpdated = accountService.findAccountById(ACCOUNT_ID_TO);
        final var accountToExpected = new AccountBuilder()
                .withId(ACCOUNT_ID_TO)
                .withBalance(BigDecimalScale.valueOf(50))
                .withOverdraft(DEFAULT_OVERDRAFT)
                .build();
        Assertions.assertThat(accountToUpdated).isEqualTo(accountToExpected);
    }

    @Test
    void givenTransferenceWithAmountGreaterThanAccountFromBalanceAndLessThanAccountOverdraft_mustTransfer() {
        final var transference = new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(ACCOUNT_ID_FROM)
                .withAccountIdTo(ACCOUNT_ID_TO)
                .withAmount(BigDecimalScale.valueOf(150))
                .build();

        accountController.transferMoney(transference);

        final var accountFromUpdated = accountService.findAccountById(ACCOUNT_ID_FROM);
        final var accountFromExpected = new AccountBuilder()
                .withId(ACCOUNT_ID_FROM)
                .withBalance(BigDecimalScale.valueOf(-50))
                .withOverdraft(DEFAULT_OVERDRAFT)
                .build();
        Assertions.assertThat(accountFromUpdated).isEqualTo(accountFromExpected);

        final var accountToUpdated = accountService.findAccountById(ACCOUNT_ID_TO);
        final var accountToExpected = new AccountBuilder()
                .withId(ACCOUNT_ID_TO)
                .withBalance(BigDecimalScale.valueOf(150))
                .withOverdraft(DEFAULT_OVERDRAFT)
                .build();
        Assertions.assertThat(accountToUpdated).isEqualTo(accountToExpected);
    }

    @Test
    void givenTransferenceWithAmountGreaterThanAccountFromAvailableAmount_mustFailToTransfer() {
        final var transference = new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(ACCOUNT_ID_FROM)
                .withAccountIdTo(ACCOUNT_ID_TO)
                .withAmount(BigDecimalScale.valueOf(250))
                .build();

        assertThrows(BusinessException.class, () ->
                accountController.transferMoney(transference));
    }

    @Test
    void givenInexistentAccountFrom_mustFailToTransfer() {
        final var transference = new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(UUID.randomUUID())
                .withAccountIdTo(ACCOUNT_ID_TO)
                .withAmount(BigDecimalScale.valueOf(100))
                .build();

        assertThrows(BusinessException.class, () ->
                accountController.transferMoney(transference));
    }

    @Test
    void givenInexistentAccountTo_mustStartTransferAndRevertPayment() {
        final var transference = new MoneyTransferInputModelTestBuilder()
                .withAccountIdFrom(ACCOUNT_ID_FROM)
                .withAccountIdTo(UUID.randomUUID())
                .withAmount(BigDecimalScale.valueOf(100))
                .build();

        accountController.transferMoney(transference);

        final var accountFromUpdated = accountService.findAccountById(ACCOUNT_ID_FROM);
        final var accountFromExpected = new AccountBuilder()
                .withId(ACCOUNT_ID_FROM)
                .withBalance(BigDecimalScale.valueOf(100))
                .withOverdraft(DEFAULT_OVERDRAFT)
                .build();
        Assertions.assertThat(accountFromUpdated).isEqualTo(accountFromExpected);
    }

}