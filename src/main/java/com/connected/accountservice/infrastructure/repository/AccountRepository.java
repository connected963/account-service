package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.domain.model.account.Account;

public interface AccountRepository {

    void insert(final Account accountToInsert);

}
