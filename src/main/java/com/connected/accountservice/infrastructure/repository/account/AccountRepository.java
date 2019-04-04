package com.connected.accountservice.infrastructure.repository.account;

import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModel;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    @SqlUpdate("insert into account(id, balance, overdraft) values (:id, :balance, :overdraft)")
    void insert(@BindBean final Account accountToInsert);

    @SqlUpdate("update account set balance = :balance, overdraft = :overdraft values where id = :id")
    void update(@BindBean final Account accountToUpdate);

    @SqlUpdate("delete account where id = :accountIdToDelete")
    void delete(final UUID accountIdToDelete);

    @SqlQuery("select id, balance from account")
    @RegisterBeanMapper(AccountQueryModel.class)
    List<AccountQueryModel> findAll();

    @RegisterBeanMapper(Account.class)
    @SqlQuery("select * from account where id = :accountId")
    Optional<Account> findById(final UUID accountId);

}
