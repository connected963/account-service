package com.connected.accountservice.application.service.account;

import com.connected.accountservice.application.inputmodel.AccountInsertInputModel;
import com.connected.accountservice.application.inputmodel.AccountInsertInputModelFactory;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModelBuilder;
import com.connected.accountservice.infrastructure.repository.account.AccountRepository;
import com.google.common.eventbus.EventBus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
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
                AccountInsertInputModelFactory.createAnDefaultWithoutOverdraft(),
                AccountInsertInputModelFactory.createAnDefaultWithOverdraft(BigDecimal.valueOf(-1))
        );
    }

    @Test
    void givenCompleteInsertInputModel_mustInsertAccount() {
        final var accountToInsert =
                AccountInsertInputModelFactory.createAnDefault();

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
                                .withBalance(BigDecimal.ZERO)
                                .build()
                ));
        final var accounts = accountService.findAll();

        Assertions.assertThat(accounts).isNotEmpty();
    }

}