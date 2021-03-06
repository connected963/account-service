package com.connected.accountservice.application.service.account;

import com.connected.accountservice.application.inputmodel.AccountInsertInputModel;
import com.connected.accountservice.application.inputmodel.AccountInsertInputModelTestFactory;
import com.connected.accountservice.application.inputmodel.MoneyTransferInputModel;
import com.connected.accountservice.application.inputmodel.MoneyTransferInputModelTestFactory;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.common.BigDecimalScale;
import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import com.connected.accountservice.domain.event.PaymentApprovedEvent;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.model.account.AccountTestFactory;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModelBuilder;
import com.connected.accountservice.infrastructure.repository.account.AccountRepository;
import com.google.common.eventbus.EventBus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceTest {

    private final AccountRepository accountRepositoryMock;

    private final MovementService movementServiceMock;

    private final EventBus eventBusMock;

    private final AccountService accountService;

    AccountServiceTest() {
        this.accountRepositoryMock = Mockito.mock(AccountRepository.class);
        this.movementServiceMock = Mockito.mock(MovementService.class);
        this.eventBusMock = Mockito.mock(EventBus.class);

        this.accountService = new AccountService(accountRepositoryMock,
                movementServiceMock, eventBusMock);
    }

    static List<AccountInsertInputModel> incompleteAccountInsertInputModelProvider() {
        return List.of(
                AccountInsertInputModelTestFactory.createAnDefaultWithoutOverdraft(),
                AccountInsertInputModelTestFactory.createAnDefaultWithOverdraft(BigDecimalScale.valueOf(-1))
        );
    }

    static List<MoneyTransferInputModel> invalidTransferInputModelProvider() {
        return List.of(
                MoneyTransferInputModelTestFactory.createAnDefaultWithoutAccountFrom(),
                MoneyTransferInputModelTestFactory.createAnDefaultWithoutAccountTo(),
                MoneyTransferInputModelTestFactory.createAnDefaultWithoutAmount(),
                MoneyTransferInputModelTestFactory.createAnDefaultWithAmount(BigDecimalScale.ZERO),
                MoneyTransferInputModelTestFactory.createAnDefaultWithAmount(BigDecimalScale.ONE.negate())
        );
    }

    @Test
    void givenCompleteInsertInputModel_mustInsertAccount() {
        final var accountToInsert =
                AccountInsertInputModelTestFactory.createAnDefault();

        final var accountId = accountService.insert(accountToInsert);

        Mockito.verify(accountRepositoryMock).insert(Mockito.any(Account.class));
        Assertions.assertThat(accountId).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("incompleteAccountInsertInputModelProvider")
    void givenInvalidModel_mustFailToInsert(final AccountInsertInputModel accountToInsert) {
        assertThrows(BusinessException.class, () -> accountService.insert(accountToInsert));
    }

    @Test
    void givenAccount_mustUpdateAccount() {
        final var accountToUpdate = AccountTestFactory.createAnDefault();

        accountService.update(accountToUpdate);

        Mockito.verify(accountRepositoryMock).update(accountToUpdate);
    }

    @Test
    void givenAccountId_mustDeleteAccount() {
        accountService.delete(AccountDefaultData.id);

        Mockito.verify(accountRepositoryMock).delete(AccountDefaultData.id);
    }

    @Test
    void mustReturnAllAccounts() {
        Mockito.when(accountRepositoryMock.findAll())
                .thenReturn(List.of(
                        new AccountQueryModelBuilder()
                                .withId(UUID.randomUUID())
                                .withBalance(BigDecimalScale.ZERO)
                                .build()
                ));
        final var accounts = accountService.findAll();

        Assertions.assertThat(accounts).isNotEmpty();
    }

    @Test
    void givenValidTransferInputModel_mustStartTransferProcess() {
        final var accountExpected = AccountTestFactory.createAnDefault();
        Mockito.when(accountRepositoryMock.findById(accountExpected.getId()))
                .thenReturn(Optional.of(accountExpected));

        final var transfer = MoneyTransferInputModelTestFactory.createAnDefault();
        accountService.transferMoney(transfer);

        final var accountWithBalanceExpected = accountExpected.withBalance(BigDecimalScale.ZERO);
        Mockito.verify(accountRepositoryMock).update(accountWithBalanceExpected);
        Mockito.verify(movementServiceMock).insert(Mockito.any(Movement.class));
        Mockito.verify(eventBusMock).post(Mockito.any(PaymentApprovedEvent.class));
    }

    @ParameterizedTest
    @MethodSource("invalidTransferInputModelProvider")
    void givenInvalidTransferInputModel_mustFailToStartTransferProcess(
            final MoneyTransferInputModel invalidTransferInputModel) {
        assertThrows(BusinessException.class,
                () -> accountService.transferMoney(invalidTransferInputModel));
    }

    @Test
    void givenInexistentAccountFrom_mustFailToStartTransferProcess() {
        final var transfer = MoneyTransferInputModelTestFactory.createAnDefault();

        assertThrows(BusinessException.class, () -> accountService.transferMoney(transfer));
    }

    @Test
    void givenExistentAccountId_mustFindAccount() {
        final var accountExpected = AccountTestFactory.createAnDefault();
        Mockito.when(accountRepositoryMock.findById(AccountDefaultData.id))
                .thenReturn(Optional.of(accountExpected));

        final var accountFound = accountService.findAccountById(AccountDefaultData.id);

        Assertions.assertThat(accountFound).isEqualTo(accountExpected);
    }

    @Test
    void givenInexistentAccountId_mustFailFindAccount() {
        assertThrows(BusinessException.class,
                () -> accountService.findAccountById(AccountDefaultData.id));
    }

}