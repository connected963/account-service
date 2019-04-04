package com.connected.accountservice.infrastructure.repository.account;

import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.querymodel.account.AccountQueryModel;
import com.connected.accountservice.infrastructure.repository.common.mapper.AccountMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    @SqlUpdate("insert into account(id, balance, overdraft) values (:id, :balance, :overdraft)")
    void insert(@BindBean final Account accountToInsert);

    @SqlUpdate("update account set balance = :balance, overdraft = :overdraft where id = :id")
    void update(@BindBean final Account accountToUpdate);

    @SqlUpdate("delete account where id = :id")
    void delete(@Bind("id") final UUID accountIdToDelete);

    @SqlQuery("select id, balance from account")
    @RegisterConstructorMapper(AccountQueryModel.class)
    List<AccountQueryModel> findAll();

    @RegisterRowMapper(AccountMapper.class)
    @SqlQuery("select * from account where id = :id")
    Optional<Account> findById(@Bind("id") final UUID accountId);

}
