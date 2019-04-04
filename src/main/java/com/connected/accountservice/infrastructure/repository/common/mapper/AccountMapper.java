package com.connected.accountservice.infrastructure.repository.common.mapper;

import com.connected.accountservice.domain.model.account.Account;
import com.connected.accountservice.domain.model.account.AccountFactory;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account map(final ResultSet resultSet, final StatementContext context) throws SQLException {
        final var accountId = resultSet.getObject("id", UUID.class);
        final var balance = resultSet.getObject("balance", BigDecimal.class);
        final var overdraft = resultSet.getObject("overdraft", BigDecimal.class);

        return AccountFactory.createExistentAccount(accountId, balance, overdraft);
    }
}
