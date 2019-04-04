package com.connected.accountservice.infrastructure.repository.account;

import com.connected.accountservice.common.BigDecimalScale;
import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.model.account.AccountTestFactory;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModelBuilder;
import com.connected.accountservice.infrastructure.repository.BaseRepositoryTest;
import com.connected.accountservice.infrastructure.repository.common.mapper.AccountMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

class AccountRepositoryTest extends BaseRepositoryTest {

    private final AccountRepository accountRepository;

    AccountRepositoryTest() {
        this.accountRepository = AccountRepositoryInjector.inject();
    }

    @Test
    void givenAccount_mustInsertAccount() {
        final var account = AccountTestFactory.createAnDefault();

        accountRepository.insert(account);

        final var accountInserted = findAccountById(account.getId());

        Assertions.assertThat(accountInserted.get()).isEqualTo(account);
    }

    private Optional<Account> findAccountById(final UUID accountId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("select * from account where id = :id")
                        .bind("id", accountId)
                        .map(new AccountMapper())
                        .findFirst());
    }

    @Test
    void givenInsertedAccount_mustUpdateAccount() {
        final var account = AccountTestFactory.createAnDefault();
        insertAccount(account);

        final var accountWithBalanceUpdated = account.withBalance(BigDecimalScale.ZERO);

        accountRepository.update(accountWithBalanceUpdated);

        final var accountUpdated = findAccountById(account.getId());

        Assertions.assertThat(accountUpdated.get()).isEqualTo(accountWithBalanceUpdated);
    }

    private void insertAccount(final Account account) {
        jdbi.useHandle(handle ->
                handle.createUpdate(
                        "insert into account(id, balance, overdraft) values (:id, :balance, :overdraft)")
                        .bindBean(account)
                        .execute());
    }

    @Test
    void givenAccountId_mustDeleteAccount() {
        final var account = AccountTestFactory.createAnDefault();
        insertAccount(account);

        accountRepository.delete(account.getId());

        final var accountDeleted = findAccountById(account.getId());

        Assertions.assertThat(accountDeleted).isEmpty();
    }

    @Test
    void mustFindAllAccounts() {
        final var account = AccountTestFactory.createAnDefault();
        insertAccount(account);

        final var accounts = accountRepository.findAll();

        final var accountQueryModelExpected = new AccountQueryModelBuilder()
                .withId(account.getId())
                .withBalance(account.getBalance())
                .build();
        Assertions.assertThat(accounts)
                .containsExactly(accountQueryModelExpected);
    }

    @Test
    void givenAccountId_mustFindAccount() {
        final var account = AccountTestFactory.createAnDefault();
        insertAccount(account);

        final var accountFound = accountRepository.findById(account.getId());

        Assertions.assertThat(accountFound.get()).isEqualTo(account);
    }
}