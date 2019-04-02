package com.connected.accountservice.infrastructure.repository.account;

import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModel;

import java.util.List;
import java.util.UUID;

public interface AccountRepository {

    void insert(final Account accountToInsert);

    void delete(final UUID accountIdToDelete);

    List<AccountQueryModel> findAll();
}
